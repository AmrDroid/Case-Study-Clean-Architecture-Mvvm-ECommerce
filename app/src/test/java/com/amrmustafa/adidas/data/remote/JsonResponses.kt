package com.amrmustafa.casestudy.data.remote.resources

val REVIEWS_SEARCH_RESULT = """
[
  {
    "id": "rat4418d1743b2f09c46bc7934a5c9a5bfb",
    "user_id": "prd050276af974ebb912e9f6c56847e7477",
    "rating": "2",
    "title": "hii",
    "description": "hii",
    "added_date": "2021-05-10 15:08:54",
    "added_date_str": "17 hours ago"
  },
  {
    "id": "ratd5ba3cc05f6e6fc73f46490a3cfcc6d4",
    "user_id": "prd050276af974ebb912e9f6c56847e7477",
    "rating": "2",
    "title": "hii",
    "description": "hii",
    "added_date": "2021-05-10 15:08:54",
    "added_date_str": "17 hours ago"
  },
  {
    "id": "ratc94c6ab9def111b8c6fe5aaec728e0e0",
    "user_id": "prd050276af974ebb912e9f6c56847e7477",
    "rating": "2",
    "title": "hii",
    "description": "hii",
    "added_date": "2021-05-10 15:08:49",
    "added_date_str": "17 hours ago"
  },
  {
    "id": "ratec04a5e8864c43fa1c3643c5627a073e",
    "user_id": "prd050276af974ebb912e9f6c56847e7477",
    "rating": "2",
    "title": "hii",
    "description": "hii",
    "added_date": "2021-05-10 15:08:49",
    "added_date_str": "17 hours ago"
  }
]
""".trimIndent()

val PRODUCT_SEARCH_RESULT = """
[
  {
    "id": "prd00e16d41d524dcd1d7544fcae0d49260",
    "name": "Beauty",
    "description": "There are many variations of passages of Lorem Ipsum available, but the majority",
    "unit_price": "10",
    "imgUrl": "https://assets.adidas.com/images/w_320,h_320,f_auto,q_auto:sensitive,fl_lossy/6634cf36274b4ea5ac46ac4e00b2021e_9366/Superstar_Shoes_Black_FY0071_01_standard.jpg",
    "currency_symbol": "${'$'}",
    "currency_short_form": "USD"
  },
  {
    "id": "prd02ef7d14d0d985e50fbe4ab09b06ecdc",
    "name": "Backpack 9",
    "description": "humour, or randomised words which don't look even slightly believable. If you ar",
    "unit_price": "15",
    "imgUrl": "https://assets.adidas.com/images/w_320,h_320,f_auto,q_auto:sensitive,fl_lossy/c7099422ccc14e44b406abec00ba6c96_9366/NMD_R1_V2_Shoes_Black_FY6862_01_standard.jpg",
    "currency_symbol": "${'$'}",
    "currency_short_form": "USD"
  },
  {
    "id": "prd04eaf02ab8d03eb66548eeb67a706ed7",
    "name": "Birthday Card 3",
    "description": "humour, or randomised words which don't look even slightly believable. If you ar",
    "unit_price": "3",
    "imgUrl": "https://assets.adidas.com/images/w_320,h_320,f_auto,q_auto:sensitive,fl_lossy/c93fa315d2f64775ac1fab96016f09d1_9366/Dame_6_Shoes_Black_FV8624_01_standard.jpg",
    "currency_symbol": "${'$'}",
    "currency_short_form": "USD"
  }
]
""".trimIndent()


val NO_SEARCH_RESULT = """
  {
    "status": "error",
    "message": "API Key is invalid"
  }
""".trimIndent()




val NOT_FOUND = """
{
  "status": "error",
  "message": "No more records"
}
""".trimIndent()


