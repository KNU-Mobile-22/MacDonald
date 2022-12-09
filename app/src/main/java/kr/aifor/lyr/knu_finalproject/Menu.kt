package kr.aifor.lyr.knu_finalproject

public class Menu(
    var name: String = "",
    var price: Int = 0,
    var code: Int = 0,
    var image_uri: Int = 0,
    var left: Int = 50
):java.io.Serializable

/**
 * MenuList 클래스
 * var
 *  list : ArrayList<Menu>(name : String, price : Int, code : Int)
 */
class MenuList {
    var isInit = 0
    var burgerList =
        //arrayListOf<Menu>()
        arrayOf(
            Menu("페퍼로니 메가 피자 버거", 10000, 101, R.drawable.c101),
            Menu("페퍼로니 피자버거", 7500, 102, R.drawable.c102),
            Menu("맥크리스피 디럭스 버거", 7500, 103, R.drawable.c103),
            Menu("맥크리스피 클래식 버거", 6700, 104, R.drawable.c104),
            Menu("트리플 치즈버거", 6600, 105, R.drawable.c105),
            Menu("필레 오 피쉬버거", 4500, 106, R.drawable.c106),
            Menu("더블 필레 오 피쉬버거", 6000, 107, R.drawable.c107),
            Menu("슈비버거", 6600, 108, R.drawable.c108),
            Menu("슈슈버거", 5500, 109, R.drawable.c109),
            Menu("1955버거", 6800, 110, R.drawable.c110),
            Menu("맥치킨", 4300, 111, R.drawable.c111),
            Menu("맥치킨 모짜렐라", 5800, 112, R.drawable.c112),
            Menu("빅맥", 5700, 113, R.drawable.c113),
            Menu("맥스파이시 상하이버거", 5700, 114, R.drawable.c114),
            Menu("더블 불고기 버거", 5300, 115, R.drawable.c115),
            Menu("에그 불고기 버거", 4300, 116, R.drawable.c116),
            Menu("불고기 버거", 3300, 117, R.drawable.c117),
            Menu("베이컨 토마토 디럭스", 6600, 118, R.drawable.c118),
            Menu("더블 쿼터파운더 치즈", 8200, 119, R.drawable.c119),
            Menu("쿼터파운더 치즈", 6300, 120, R.drawable.c120),
            Menu("치즈버거", 3300, 121, R.drawable.c121),
            Menu("더블 치즈버거", 5300, 122, R.drawable.c122),
            Menu("햄버거", 3000, 123, R.drawable.c123)
        )
    var sideList =
        //arrayListOf<Menu>()
        arrayOf(
            Menu("후렌치 후라이 스몰", 1900, 201, R.drawable.c201),
            Menu("후렌치 후라이 미디엄", 2600, 202, R.drawable.c202),
            Menu("후렌치 후라이 라지", 3200, 203, R.drawable.c203),
            Menu("치킨 토마토 스낵랩", 3000, 204, R.drawable.c204),
            Menu("코우슬로", 2700, 205, R.drawable.c205),
            Menu("골든 모짜렐라 치즈스틱 2조각", 3300, 206, R.drawable.c206),
            Menu("골든 모짜렐라 치즈스틱 4조각", 5000, 207, R.drawable.c207),
            Menu("맥너겟 4조각", 3000, 208, R.drawable.c208),
            Menu("상하이 치킨 스낵랩", 3200, 209, R.drawable.c209)
        )
    var drinkList =
        //arrayListOf<Menu>()
        arrayOf(
            Menu("딸기 칠러", 3700, 301, R.drawable.c301),
            Menu("제주 한라봉 칠러", 3700, 302, R.drawable.c302),
            Menu("코카 콜라", 2200, 303, R.drawable.c303),
            Menu("코카 콜라 제로", 2200, 304, R.drawable.c304),
            Menu("환타", 2200, 305, R.drawable.c305),
            Menu("스프라이트", 2200, 306, R.drawable.c306),
            Menu("스프라이트 제로", 2200, 307, R.drawable.c307),
            Menu("딸기 쉐이크", 3500, 308, R.drawable.c308),
            Menu("초코 쉐이크", 3500, 309, R.drawable.c309)
        )
    var coffeeList =
        //arrayListOf<Menu>()
        arrayOf(
            Menu("바닐라 라떼", 4700, 401, R.drawable.c401),
            Menu("아이스 바닐라 라떼", 4200, 402, R.drawable.c402),
            Menu("카페라떼", 3700, 403, R.drawable.c403),
            Menu("카푸치노", 3700, 404, R.drawable.c404),
            Menu("아메리카노", 3200, 405, R.drawable.c405),
            Menu("드립 커피", 2700, 406, R.drawable.c406),
            Menu("아이스 드립 커피", 2200, 407, R.drawable.c407),
            Menu("아이스 아메리카노", 3200, 408, R.drawable.c408),
            Menu("아이스 카페라떼", 3700, 409, R.drawable.c409),
            Menu("에스프레소", 2400, 410, R.drawable.c410)
        )
    var desertList =
        //arrayListOf<Menu>()
        arrayOf(
            Menu("디핑 소스 스위트 앤 사워", 300, 501, R.drawable.c501),
            Menu("디핑 소스 스위트 칠리", 300, 502, R.drawable.c502),
            Menu("디핑 소스 케이준", 300, 503, R.drawable.c503),
            Menu("스트링 치즈", 2400, 504, R.drawable.c504),
            Menu("츄러스", 2500, 505, R.drawable.c505),
            Menu("애플파이", 2000, 506, R.drawable.c506),
            Menu("오레오 맥플러리", 3400, 507, R.drawable.c507),
            Menu("딸기 선데이 아이스크림", 2500, 508, R.drawable.c508),
            Menu("초코 선데이 아이스크림", 2500, 509, R.drawable.c509),
            Menu("바닐라 선데이 아이스크림", 250, 510, R.drawable.c510)
        )
}