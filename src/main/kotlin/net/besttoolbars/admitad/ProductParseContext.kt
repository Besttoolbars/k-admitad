package net.besttoolbars.admitad

import net.besttoolbars.admitad.response.product.AdmitadProductMerchant
import net.besttoolbars.admitad.response.product.AdmitadProductOffer

data class ProductParseContext(val product: AdmitadProductOffer, val merchant: AdmitadProductMerchant)
