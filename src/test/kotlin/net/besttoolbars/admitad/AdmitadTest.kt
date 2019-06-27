package net.besttoolbars.admitad

import net.besttoolbars.admitad.response.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class AdmitadTest {
    @Test
    fun tokenHttpTest() {
        val stream = this.javaClass.classLoader.getResourceAsStream("testdata/token/test.json")!!
        val admitad = Admitad(httpHandler = MockAdmitad(stream))
        val token = admitad.token("", "272259", "").get()
        val exprected = OAuth2Token(
            username = "user",
            firstName = "first name",
            lastName = "last name",
            group = "webmaster",
            language = "ru",
            accessToken = "token",
            expiresIn = 604800,
            tokenType = "bearer",
            scope = "coupons_for_website banners advcampaigns_for_website websites public_data",
            id = 785539,
            refreshToken = "reftoken"
        )
        assertEquals(exprected, token)
    }

    @Test
    fun couponHttpTest() {
        val stream = this.javaClass.classLoader.getResourceAsStream("testdata/coupon/test.json")!!
        val expected = AdmitadCouponResponse(
            meta = AdmitadMeta(count = 2941, limit = 2, offset = 0),
            results = listOf(
                AdmitadCoupon(
                    status = "active",
                    rating = "0.00",
                    dateStart = "2019-03-13T00:00:00",
                    campaign = AdmitadCouponCampaign(
                        id = 16040,
                        name = "Brionity",
                        siteUrl = "https://brionity.com/"
                    ),
                    shortName = "Распродажа туфель! Cкидка до 70% + 40%!",
                    exclusive = false,
                    name = "Распродажа туфель! Cкидка до 70% + 40%!",
                    dateEnd = "2019-05-31T23:59:00",
                    promocode = "НЕ НУЖЕН",
                    id = 236339,
                    regions = listOf("RU"),
                    discount = "70%",
                    types = listOf(
                        AdmitadCouponType(
                            id = 2,
                            name = "Скидка на заказ"
                        )
                    ),
                    image = "http://cdn.admitad.com/campaign/images/2016/10/06/06963120ff90c4564ff4b765e20c140d.jpg",
                    framesetLink = "",
                    gotoLink = "https://ad.admitad.com/g/su6romn4gs162aae1f4e09008834d3/?i=3",
                    species = "action",
                    categories = listOf(
                        AdmitadCouponCategory(3, "Аксессуары и сумки"),
                        AdmitadCouponCategory(4, "Обувь "),
                        AdmitadCouponCategory(5, "Одежда")
                    ),
                    description = ""
                )
            )
        )
        val admitad = Admitad(httpHandler = MockAdmitad(stream))
        val response = admitad.coupons("", "272259", "").get()
        assertEquals(expected, response)
    }

    @Test
    fun advHttpTest() {
        val stream = this.javaClass.classLoader.getResourceAsStream("testdata/adv/test.json")!!
        val expected = AdmitadAdvResponse(
            meta = AdmitadMeta(count = 666, limit = 1, offset = 0),
            results = listOf(
                AdmitadAdv(
                    gotoCookieLifetime = 30,
                    rating = 3.0f,
                    exclusive = false,
                    mobileOsType = null,
                    image = "http://cdn.admitad.com/campaign/images/2017/10/3/fffe92da99b37d46b356b84ea1a6b270.jpg",
                    actions = listOf(
                        AdmitadAdvAction(
                            holdTime = 0,
                            paymentSize = "7%",
                            type = "sale",
                            name = "Оплаченный заказ на сайте",
                            id = 14854
                        )
                    ),
                    retag = false,
                    currency = "RUB",
                    activationDate = "2011-11-23T00:11:08",
//      "actions_limit": null,
                    cr = 0.59f,
                    ecpc = 0.92f,
                    id = 153,
//      "allow_actions_all_countries": true,
                    avgHoldTime = 54,
                    connectionStatus = "active",
                    gotolink = "https://pafutos.com/g/2559705e24162aae1f4e4b9351d46e/",
//      "mobile_device_type": null,
                    avgMoneyTransferTime = 54,
                    siteUrl = "http://kupivip.ru/",
                    regions = listOf(
                        AdmitadAdvRegion("RU")
                    ),
//      "actions_detail": [
//        {
//          "hold_size": 0,
//          "tariffs": [
//            {
//              "action_id": 14854,
//              "rates": [
//                {
//                  "price_s": "0.00",
//                  "tariff_id": 19266,
//                  "country": null,
//                  "date_s": "2019-03-07",
//                  "is_percentage": true,
//                  "id": 186466,
//                  "size": "7.00"
//                }
//              ],
//              "id": 19266,
//              "name": "Тариф по умолчанию"
//            }
//          ],
//          "type": "sale",
//          "name": "Оплаченный заказ на сайте",
//          "id": 14854
//        }
//      ],
//      "landing_title": null,
                    geotargeting = false,
//      "mobile_os": null,
                    status = "active",
                    couponIframeDenied = false,
                    traffics = listOf(
                        AdmitadAdvTraffic(
                            enabled = true,
                            name = "Cashback",
                            id = 1
                        )
                    ),
                    description = "description",
                    crTrend = 0.0000f,
                    rawDescription = "raw description",
                    modifiedDate = "2019-03-13T14:42:45",
                    denynewwms = false,
//      "action_countries": [
//        "AD",
//        "AE",
//      ],
//      "moderation": false,
//      "action_testing_limit": null,
                    maxHoldTime = null,
                    categories = listOf(
                        AdmitadAdvCategory(
                            language = "ru",
                            id = 62,
                            parent = null,
                            name = "Интернет-магазины"
                        ),
                        AdmitadAdvCategory(
                            language = "ru",
                            id = 64,
                            parent = AdmitadAdvCategory(
                                language = "ru",
                                id = 62,
                                parent = null,
                                name = "Интернет-магазины"
                            ),
                            name = "Одежда & Обувь"
                        )
                    ),
                    productsCsvLink = "http://products_csv_link",
                    productsXmlLink = "http://products_xml_link",
                    name = "Kupivip RU",
                    feedsInfo = listOf(
                        AdmitadAdvFeedInfo(
                            advertiserLastUpdate = "2019-03-13 11:18:21.540000",
                            admitadLastUpdate = "2019-03-13 09:03:23",
                            csvLink = "http://products_xml_link",
                            name = "Основной",
                            xmlLink = "http://xml_link"
                        )
                    ),
//      "actions_limit_24": null,
//      "landing_code": null,
                    ecpcTrend = -0.0700f,
                    epcTrend = -7.0000f,
                    epc = 92f,
                    allowDeeplink = true,
                    showProductsLinks = true
                )
            )
        )
        val admitad = Admitad(httpHandler = MockAdmitad(stream))
        val response = admitad.adv("", "272259", listOf()).get()
        assertEquals(expected, response)
    }
}
