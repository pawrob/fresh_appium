package org.fresh;

import org.fresh.helpers.LoginHelper;
import org.fresh.pages.shop.ShopPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TestShopSorting extends BaseTest {


    @Test(groups = {"android"}, dataProvider = "loginData")
    public void testUserCanSortByPriceDescending(String username, String password) {
        LoginHelper loginHelper = new LoginHelper(driver);
        ShopPage shopPage = loginHelper.loginToShop(username, password);

        shopPage.clickSortingButtonAndOpenModal();
        shopPage.clickSortByPriceHighToLow();
        List<Double> pricesHighToLow = shopPage.getPricesOfVisibleItems();

        Assert.assertTrue(pricesHighToLow.get(0) > pricesHighToLow.get(1), "First price is not bigger than second price!");
    }

    @Test(groups = {"android"}, dataProvider = "loginData")
    public void testUserCanSortByPriceAscending(String username, String password) {
        LoginHelper loginHelper = new LoginHelper(driver);
        ShopPage shopPage = loginHelper.loginToShop(username, password);

        shopPage.clickSortingButtonAndOpenModal();
        shopPage.clickSortByPriceLowToHigh();
        List<Double> pricesLowToHigh = shopPage.getPricesOfVisibleItems();

        Assert.assertTrue(pricesLowToHigh.get(0) < pricesLowToHigh.get(1), "First price is not smaller than second price!");
    }

    @Test(groups = {"android"}, dataProvider = "loginData")
    public void testUserCanSortByNameAscending(String username, String password) {
        LoginHelper loginHelper = new LoginHelper(driver);
        ShopPage shopPage = loginHelper.loginToShop(username, password);

        shopPage.clickSortingButtonAndOpenModal();
        shopPage.clickSortByNameAToZ();
        List<String> namesAscending = shopPage.getNamesOfVisibleItems();

        Assert.assertTrue(namesAscending.get(0).compareTo(namesAscending.get(1)) <= 0, "Names are not sorted in ascending order!");
    }

    @Test(groups = {"android"}, dataProvider = "loginData")
    public void testUserCanSortByNameDescending(String username, String password) {
        LoginHelper loginHelper = new LoginHelper(driver);
        ShopPage shopPage = loginHelper.loginToShop(username, password);

        shopPage.clickSortingButtonAndOpenModal();
        shopPage.clickSortByNameZToA();
        List<String> namesDescending = shopPage.getNamesOfVisibleItems();

        Assert.assertTrue(namesDescending.get(0).compareTo(namesDescending.get(1)) >= 0, "Names are not sorted in descending order!");
    }
}
