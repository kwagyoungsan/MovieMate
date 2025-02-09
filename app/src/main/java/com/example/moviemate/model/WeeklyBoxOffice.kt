package com.example.moviemate.model

/**
 * "boxOfficeResult": {
 *     "boxofficeType": "주간 박스오피스",
 *     "showRange": "20141229~20150104",
 *     "yearWeekTime": "201501",
 *     "weeklyBoxOfficeList": [
 *       {
 *         "rnum": "1",
 *         "rank": "1",
 *         "rankInten": "0",
 *         "rankOldAndNew": "OLD",
 *         "movieCd": "20137048",
 *         "movieNm": "국제시장",
 *         "openDt": "2014-12-17",
 *         "salesAmt": "27120029765",
 *         "salesShare": "41.2",
 *         "salesInten": "5817915513",
 *         "salesChange": "27.3",
 *         "salesAcc": "60542159661",
 *         "audiCnt": "3471300",
 *         "audiInten": "744752",
 *         "audiChange": "27.3",
 *         "audiAcc": "7752903",
 *         "scrnCnt": "1044",
 *         "showCnt": "31586"
 *       },
 *       {
 *         "rnum": "2",
 *         "rank": "2",
 *         "rankInten": "0",
 *         "rankOldAndNew": "NEW",
 *         "movieCd": "20143642",
 *         "movieNm": "테이큰 3",
 *         "openDt": "2015-01-01",
 *         "salesAmt": "8930451200",
 *         "salesShare": "13.6",
 *         "salesInten": "8930451200",
 *         "salesChange": "100.0",
 *         "salesAcc": "8930451200",
 *         "audiCnt": "1109140",
 *         "audiInten": "1109140",
 *         "audiChange": "100.0",
 *         "audiAcc": "1109140",
 *         "scrnCnt": "616",
 *         "showCnt": "13265"
 *       },
 *       {
 *         "rnum": "3",
 *         "rank": "3",
 *         "rankInten": "26",
 *         "rankOldAndNew": "OLD",
 *         "movieCd": "20149859",
 *         "movieNm": "마다가스카의 펭귄",
 *         "openDt": "2014-12-31",
 *         "salesAmt": "6580252859",
 *         "salesShare": "10.0",
 *         "salesInten": "6563160859",
 *         "salesChange": "38399.0",
 *         "salesAcc": "6609252859",
 *         "audiCnt": "856527",
 *         "audiInten": "854607",
 *         "audiChange": "44510.8",
 *         "audiAcc": "859778",
 *         "scrnCnt": "615",
 *         "showCnt": "8996"
 *       },
 *       {
 *         "rnum": "4",
 *         "rank": "4",
 *         "rankInten": "-2",
 *         "rankOldAndNew": "OLD",
 *         "movieCd": "20149265",
 *         "movieNm": "기술자들",
 *         "openDt": "2014-12-24",
 *         "salesAmt": "6342171235",
 *         "salesShare": "9.6",
 *         "salesInten": "-4693793813",
 *         "salesChange": "-42.5",
 *         "salesAcc": "17446004183",
 *         "audiCnt": "825700",
 *         "audiInten": "-588985",
 *         "audiChange": "-41.6",
 *         "audiAcc": "2249423",
 *         "scrnCnt": "597",
 *         "showCnt": "15018"
 *       },
 */

data class WeeklyBoxOffice(
    val rank: String,
    val movieNm: String,
    val openDt: String,
    val audiAcc: String,
    val rankInten: String,
    val rankOldAndNew: String
)

data class WeeklyBoxOfficeResponse(
    val boxOfficeResult: WeeklyBoxOfficeResult
)

data class WeeklyBoxOfficeResult(
    val boxOfficeList: List<WeeklyBoxOffice>
)
