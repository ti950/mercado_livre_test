package com.br.mltest.presentation

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.br.mltest.R
import com.br.mltest.model.Address
import com.br.mltest.model.Installments
import com.br.mltest.model.Item
import com.br.mltest.model.Seller
import com.br.mltest.viewmodel.ItemResult
import com.br.mltest.viewmodel.SearchRepository
import com.br.mltest.viewmodel.SearchViewModel
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class SearchViewModelTest {

//    @Rule
//    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var itemsLiveDataObserver: Observer<List<Item>>

    @Mock
    private lateinit var viewFieldLiveDataObserver: Observer<Int>

    private lateinit var viewModel: SearchViewModel

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(SearchViewModelTest::class)
    }

    @Test
    fun `when view model getSearchItem get success then set booksLiveData`() {

        val seller = Seller("permalink", "date")
        val installments = Installments(1, 90.0f)
        val address = Address("state", "city")
        // Arrange
        val item = listOf(
            Item("id13334", "titel", "price",
                "thumbnail", seller = seller, "available_quantity",
                "sold_quantity", "condition", installments, address)
        )
        val resultSuccess = MockRepository(ItemResult.Success(item))
        viewModel = SearchViewModel(resultSuccess)
        viewModel.itemLiveData.observeForever(itemsLiveDataObserver)

        // Act
        viewModel.getSearchItem("Motorola")

        // Assert
        verify(itemsLiveDataObserver).onChanged(item)
    }

    @Test
    fun `when view model getSearchItem get api error 401`() {

        val statusCode = 401

        //Arrange
        val resultApiError = MockRepository(ItemResult.ApiError(statusCode))
        viewModel = SearchViewModel(resultApiError)

        //Act
        viewModel.getSearchItem(query = "Motorola")

        // Assert
        verify(viewFieldLiveDataObserver).onChanged(R.string.error_401)
    }

    @Test
    fun `when view model getSearchItem get api error 400 then set viewFlipperData`() {

        val statusCode = 400

        //Arrange
        val resultApiError = MockRepository(ItemResult.ApiError(statusCode))
        viewModel = SearchViewModel(resultApiError)
        viewModel.viewFieldLiveData.observeForever(viewFieldLiveDataObserver)

        //Act
        viewModel.getSearchItem("Motorola")

        // Assert
        verify(viewFieldLiveDataObserver).onChanged(R.string.error_400_generic)
    }

    @Test
    fun `when view model getSearchItem get error then set viewFlipperData`() {

        //Arrange
        val resultServerError = MockRepository(ItemResult.ServerError)
        viewModel = SearchViewModel(resultServerError)
        viewModel.viewFieldLiveData.observeForever(viewFieldLiveDataObserver)

        //Act
        viewModel.getSearchItem("Motorola")

        // Assert
        verify(viewFieldLiveDataObserver).onChanged(R.string.error_500_generic)
    }

}

class MockRepository(private val result: ItemResult): SearchRepository {
    override fun searchItem(query: String, itemResultsCallback: (result: ItemResult) -> Unit) {
        itemResultsCallback(result)
    }

    override fun network(context: Context): Boolean {
        return true
    }
}