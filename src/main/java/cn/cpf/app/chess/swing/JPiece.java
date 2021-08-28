package cn.cpf.app.chess.swing;

import cn.cpf.app.chess.conf.ChessDefined;
import cn.cpf.app.chess.modal.Part;
import cn.cpf.app.chess.modal.Piece;
import cn.cpf.app.chess.modal.Place;
import cn.cpf.app.chess.util.SwingUtils;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.*;

/**
 * <b>Description : </b>
 *
 * @author CPF
 * Date: 2020/3/18 14:39
 */
@Slf4j
public class JPiece {

    @Getter
    private final JLabel comp;

    @Getter
    private Place place;

    public JPiece(Image image) {
        this(image, null);
    }

    public JPiece(Image image, Place place) {
        comp = new JLabel();
        comp.setSize(ChessDefined.PIECE_WIDTH, ChessDefined.PIECE_HEIGHT);
        comp.setIcon(new ImageIcon(image));
        if (place == null) {
            this.hide();
        } else {
            this.setPlace(place);
        }
    }

    public JPiece(Piece piece, Place place) {
        comp = new JLabel();
        comp.setSize(ChessDefined.PIECE_WIDTH, ChessDefined.PIECE_HEIGHT);
        comp.setText(piece.name().replaceAll("Red|Black", ""));
        if (piece.part == Part.RED) {
            comp.setForeground(Color.red);
        } else {
            comp.setForeground(Color.black);
        }
        if (place == null) {
            this.hide();
        } else {
            this.setPlace(place);
        }
    }

    private void setPlace(int x, int y) {
        comp.setLocation(ChessDefined.convertPlaceToLocation(x, y));
    }

    public void hide() {
        this.setVisible(false);
        this.setPlace(-1, -1);
    }

    /**
     * 棋子直接移动到棋盘上 place 对应的坐标
     */
    public void setPlace(@NonNull Place place) {
        this.place = place;
        this.setPlace(place.x, place.y);
    }

    /**
     * 棋子移动到棋盘上 place 对应的坐标(带动画)
     */
    public void movePlace(@NonNull Place place) {
        this.place = place;
        Point toPoint = ChessDefined.convertPlaceToLocation(place.x, place.y);
        try {
            SwingUtils.moveComp(comp, toPoint);
        } catch (InterruptedException e) {
            log.error("", e);
            Thread.currentThread().interrupt();
        }
    }

    public void setPlaceAndShow(Place place) {
        this.setPlace(place);
        this.setVisible(true);
    }

    public void setVisible(boolean visible) {
        comp.setVisible(visible);
    }

}