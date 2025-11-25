package com.pullapart.core;

import com.pullapart.pages.*;
import org.openqa.selenium.WebDriver;

public class PageManager {

    private static ThreadLocal<PageManager> pageManager = new ThreadLocal<>();

    private HomePage homePage;
    private FAQPage faqPage;
    private UsedCarsPage usedCarsPage;
    private VIPClubPage vipClubPage;
    private InventorySearchPage inventorySearchPage;

    private PageManager() {}

    // Initialize per test run
    public static void init() {
        pageManager.set(new PageManager());
    }

    public static PageManager getInstance() {
        if (pageManager.get() == null) {
            pageManager.set(new PageManager());
        }
        return pageManager.get();
    }

    public HomePage home() {
        if (homePage == null) {
            homePage = new HomePage();
        }
        return homePage;
    }

    public FAQPage faq() {
        if (faqPage == null) {
            faqPage = new FAQPage();
        }
        return faqPage;
    }

    public UsedCarsPage usedCars() {
        if (usedCarsPage == null) {
            usedCarsPage = new UsedCarsPage();
        }
        return usedCarsPage;
    }

    public VIPClubPage vipClub() {
        if (vipClubPage == null) {
            vipClubPage = new VIPClubPage();
        }
        return vipClubPage;
    }

    public InventorySearchPage inventorySearch() {
        if (inventorySearchPage == null) {
            inventorySearchPage = new InventorySearchPage();
        }
        return inventorySearchPage;
    }

    public static void unload() {
        pageManager.remove();
    }
}
