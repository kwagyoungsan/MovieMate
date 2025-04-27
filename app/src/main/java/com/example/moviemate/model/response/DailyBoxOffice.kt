package com.example.moviemate.model.response

/**
 * <dailyBoxOffice>
 * <rnum>1</rnum>
 * <rank>1</rank>
 * <rankInten>0</rankInten>
 * <rankOldAndNew>OLD</rankOldAndNew>
 * <movieCd>20203702</movieCd>
 * <movieNm>노량: 죽음의 바다</movieNm>
 * <openDt>2023-12-20</openDt>
 * <salesAmt>2893509165</salesAmt>
 * <salesShare>38.8</salesShare>
 * <salesInten>-686119312</salesInten>
 * <salesChange>-19.2</salesChange>
 * <salesAcc>36926118105</salesAcc>
 * <audiCnt>290717</audiCnt>
 * <audiInten>-59184</audiInten>
 * <audiChange>-16.9</audiChange>
 * <audiAcc>3728850</audiAcc>
 * <scrnCnt>1706</scrnCnt>
 * <showCnt>5793</showCnt>
 * </dailyBoxOffice>
 */

data class DailyBoxOffice(
    val rank: String,
    val movieNm: String,
    val openDt: String,
    val audiAcc: String,
    val rankOldAndNew: String,
    val movieCd: String
)

data class DailyBoxOfficeResponse(
    val boxOfficeResult: DailyBoxOfficeResult
)

data class DailyBoxOfficeResult(
    val dailyBoxOfficeList: List<DailyBoxOffice>
)

