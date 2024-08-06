package com.example.moviemate.model

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
    val rnum: Int,
    val rank: Int,
    val rankInten: Int,
    val rankOldAndNew: Int,
    val movieCd: Int,
    val movieNm: String,
    val openDt: String,
    val salesShare: Double,
    val salesInten: Double,
    val salesChange: Double,
    val salesAcc: Int,
    val audiCnt: Int,
    val audiInten: Double,
    val audiChange: Double,
    val audiAcc: Int,
    val scrnCnt: Int,
    val showCnt: Int
)