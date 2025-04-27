package com.example.moviemate.model.response

/**
 * movieCd		영화코드를 출력합니다.
 * movieNm		영화명(국문)을 출력합니다.
 * prdtYear		제작연도를 출력합니다.
 * showTm		상영시간을 출력합니다.
 * openDt		개봉연도를 출력합니다.
 * prdtStatNm	제작상태명을 출력합니다.
 * nations      제각 국가를 나타냅니다.
 * nationNm		제작국가명을 출력합니다.
 * genreNm		장르명을 출력합니다.
 * directors	감독을 나타냅니다.
 * peopleNm		감독명을 출력합니다.
 * actors		배우를 나타냅니다.
 * peopleNm		배우명을 출력합니다.
 * cast		배역명을 출력합니다. -> 빈 값일 수도 있음. 빈 값일 경우에는 안보이도록 처리
 * watchGradeNm		관람등급 명칭을 출력합니다.
 */

data class DetailInfo(
    val movieCd: String,
    val movieNm: String,
    val prdtYear: String,
    val showTm: String,
    val openDt: String,
    val prdtStatNm: String,
    val nations: List<Nation>,
    val genreNm: String,
    val directors: List<Director>,
    val actors: List<Actor>,
    val cast: String, // 추가 데이터 없으면 여기는 놔둬도 됨
    val watchGradeNm: String
)

data class Nation(
    val nationNm: String
)

data class Director(
    val peopleNm: String
)

data class Actor(
    val peopleNm: String,
    val cast: String? = null
)


data class DetailInfoResponse(
    val movieInfoResult: MovieInfoResult
)

data class MovieInfoResult(
    val movieInfo: DetailInfo
)
