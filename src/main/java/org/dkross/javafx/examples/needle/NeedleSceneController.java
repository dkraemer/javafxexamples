package org.dkross.javafx.examples.needle;


import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public final class NeedleSceneController {

    private boolean isCheckBoxShowTriangleFirstClick;

    private MouseEvent mouseEventSaved = null;

    @FXML
    private ImageView imageView;
    @FXML
    private Label labelMousePosition;
    @FXML
    private CheckBox checkBoxShowTriangle;

    @FXML
    private Line lineOppositeLeg;
    @FXML
    private Line lineAdjacentLeg;
    @FXML
    private Line lineHypotenuse;

    @FXML
    private Circle circleAlpha;
    @FXML
    private Circle circleBeta;
    @FXML
    private Circle circleGamma;


    @FXML
    private void initialize() {
        isCheckBoxShowTriangleFirstClick = true;
        setTriangleVisible(false);
    }

    @FXML
    private void onMouseMoved_anchorPane(MouseEvent mouseEvent) {
        // Triangle point A
        double needleImageCenterX = imageView.getLayoutX() + imageView.getFitWidth() / 2;
        double needleImageCenterY = imageView.getLayoutY() + imageView.getFitHeight() / 2;
        // Triangle point B
        double mouseCurrentX = mouseEvent.getSceneX();
        double mouseCurrentY = mouseEvent.getSceneY();
        // Triangle leg a
        double oppositeLeg = mouseCurrentX - needleImageCenterX;
        // Triangle leg b
        double adjacentLeg = needleImageCenterY - mouseCurrentY;

        // Calculate rotation angle
        double rotationAngle = Math.toDegrees(Math.atan(oppositeLeg / adjacentLeg));

        // Handle rotation angles greater than 90 degrees
        if (mouseCurrentY > needleImageCenterY) {
            rotationAngle += 180;
        }

        // Rotate image based on previous calculation
        imageView.setRotate(rotationAngle);

        // Update triangle coordinates only if desired
        if (checkBoxShowTriangle.isSelected()) {
            lineAdjacentLeg.setStartX(needleImageCenterX);
            lineAdjacentLeg.setStartY(needleImageCenterY);
            lineAdjacentLeg.setEndX(needleImageCenterX);
            lineAdjacentLeg.setEndY(mouseCurrentY);

            lineOppositeLeg.setStartX(needleImageCenterX);
            lineOppositeLeg.setStartY(mouseCurrentY);
            lineOppositeLeg.setEndX(mouseCurrentX);
            lineOppositeLeg.setEndY(mouseCurrentY);

            lineHypotenuse.setStartX(needleImageCenterX);
            lineHypotenuse.setStartY(needleImageCenterY);
            lineHypotenuse.setEndX(mouseCurrentX);
            lineHypotenuse.setEndY(mouseCurrentY);

            circleAlpha.setCenterX(needleImageCenterX);
            circleAlpha.setCenterY(needleImageCenterY);

            circleBeta.setCenterX(mouseCurrentX);
            circleBeta.setCenterY(mouseCurrentY);

            circleGamma.setCenterX(needleImageCenterX);
            circleGamma.setCenterY(mouseCurrentY);

            labelMousePosition.setText(String.format("Mouse X:%03.0f Y:%03.0f", mouseCurrentX, mouseCurrentY));
        }
        mouseEventSaved = mouseEvent;
    }

    @FXML
    private void onMouseClicked_checkBoxShowTriangle(MouseEvent mouseEvent) {
        setTriangleVisible(checkBoxShowTriangle.isSelected());

        if (isCheckBoxShowTriangleFirstClick) {
            isCheckBoxShowTriangleFirstClick = false;

            lineAdjacentLeg.setLayoutX(0);
            lineAdjacentLeg.setLayoutY(0);
            lineOppositeLeg.setLayoutX(0);
            lineOppositeLeg.setLayoutY(0);
            lineHypotenuse.setLayoutX(0);
            lineHypotenuse.setLayoutY(0);

            if (mouseEventSaved != null) {
                onMouseMoved_anchorPane(mouseEventSaved);
            } else {
                onMouseMoved_anchorPane(mouseEvent);
            }
        }
    }

    private void setTriangleVisible(boolean triangleVisible) {
        labelMousePosition.setVisible(triangleVisible);
        circleAlpha.setVisible(triangleVisible);
        circleBeta.setVisible(triangleVisible);
        circleGamma.setVisible(triangleVisible);
        lineOppositeLeg.setVisible(triangleVisible);
        lineAdjacentLeg.setVisible(triangleVisible);
        lineHypotenuse.setVisible(triangleVisible);
    }
}
