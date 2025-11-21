package com.pullapart.locators;

import org.openqa.selenium.By;

public class CommonLocators {

    public static final By VIDEOS_LINK_XPATH = By.xpath("//a[@class='nav-link  desktop-only'][contains(text(),'VIDEOS')]");
    public static final By FIND_A_STORE_LINK_XPATH = By.xpath("//a[@class='nav-link desktop-only'][contains(text(),'FIND A STORE'')]");
    public static final By VIP_CLUB_LINK_XPATH = By.xpath("//a[@class='nav-link desktop-only'][contains(text(),'VIP Club')]");
    public static final By USED_CARS_LINK_XPATH = By.xpath("//a[@class='nav-link desktop-only'][contains(text(),'Used Cars')]");
    public static final By FAQ_LINK_XPATH = By.xpath("//a[@class='nav-link  desktop-only'][contains(text(),'FAQ')]");
}
