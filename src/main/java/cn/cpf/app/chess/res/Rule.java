package cn.cpf.app.chess.res;

import cn.cpf.app.chess.bean.AnalysisBean;
import cn.cpf.app.chess.bean.ChessPiece;

import java.util.List;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * Date: 2020/3/19 17:17
 */
public interface Rule {

    /**
     * 检查 part 方在 piece棋盘上 由 from 至 to 的走棋是否符合规则
     *
     * 前提步骤
     * 1. from != to
     * 2. from, to坐标都在棋盘之内
     *
     * 检查
     * 1. 范围是否符合规则, 将, 士, 相是否在规定的区域内
     * 2. 是否有阻碍, 阻象眼或蹩马腿等
     * 3. 终点是否为空位或者是对方的棋子
     * 4. 路径是否符合规则, 车, 炮 走直线, 马走日等
     *
     * @param pieces 棋盘
     * @param part 当前走棋方
     * @param from 初始位置
     * @param to   走棋后位置
     * @return 当前走棋是否符合规则
     */
    boolean check(ChessPiece[][] pieces, Part part, Place from, Place to);

    default List<Place> find(ChessPiece[][] pieces, Part part, Place place) {
        throw new RuntimeException();
    }

    default List<Place> find(AnalysisBean analysisBean, Part part, Place place) {
        throw new RuntimeException();
    }

    default int checkPlace(ChessPiece chessPiece, Part part) {
        if (chessPiece == null) {
            return 0;
        }
        if (chessPiece.part == part) {
            return -1;
        }
        return 1;
    }

    default void addPlaceIntoListWhenXyIsNull(ChessPiece[][] chessPieces, Part part, List<Place> list, Place place, int x, int y) {
        if (chessPieces[x][y] != null) {
            return;
        }
        ChessPiece piece = chessPieces[place.x][place.y];
        if (piece == null || piece.part != part) {
            list.add(place);
        }
    }

    /**
     * 如果 part 方在 chessPiece 棋盘里面可以一步走到 place的位置, 则将 place 加入到 list 列表中
     *
     * @param chessPieces 棋盘
     * @param part 当前走棋方
     * @param list 列表
     * @param place 位置
     */
    default void addPlaceIntoList(ChessPiece[][] chessPieces, Part part, List<Place> list, Place place) {
        ChessPiece piece = chessPieces[place.x][place.y];
        if (piece == null || piece.part != part) {
            list.add(place);
        }
    }

    /**
     * 1. 每个棋子本身的价值
     * 3. 棋子的地理优势
     * 2. 棋子评分
     * 	1. 棋子嘴边多少food
     * 	2. 棋子相关的点, 有影响的有多少点
     */
    default int getScore(ChessPiece[][] chessPieces, Place place) {
        return 0;
    }
}