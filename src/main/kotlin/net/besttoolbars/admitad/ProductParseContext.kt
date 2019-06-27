package net.besttoolbars.admitad

import net.besttoolbars.admitad.response.AdmitadProductMerchant
import net.besttoolbars.admitad.response.AdmitadProductOffer

data class ProductParseContext(val product: AdmitadProductOffer, val merchant: AdmitadProductMerchant)
