package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Main extends Application {
    private int money=0;
    private int totalComrades=0;
    private int upgradeCost=10;
    private int multiplier=1;
    private boolean rotFlag=true;
    private int base=1;
    private long startTime;
    private long endTime;

    @Override
    public void start(Stage primaryStage) throws Exception{
        startTime = System.currentTimeMillis();
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Doge Clicker");

        Image dogImage = new Image("http://i.imgur.com/CLv9rzn.jpg");
        ImageView dogImageView = new ImageView(dogImage);
        dogImageView.setX(300);
        dogImageView.setY(200);
        dogImageView.setFitHeight(420);
        dogImageView.setFitWidth(520);
        dogImageView.setPreserveRatio(true);

        Image x2Image = new Image("http://i.imgur.com/jPpTo0z.jpg");
        ImageView x2ImageView = new ImageView(x2Image);
        x2ImageView.setX(50);
        x2ImageView.setY(600);
        x2ImageView.setFitWidth(200);
        x2ImageView.setFitHeight(300);
        x2ImageView.setPreserveRatio(true);

        Image endImage = new Image("http://i.imgur.com/qcWB19x.jpg");
        ImageView endImageView = new ImageView(endImage);
        endImageView.setX(800);
        endImageView.setY(600);
        endImageView.setFitHeight(200);
        endImageView.setFitWidth(250);

        Text scoreText = new Text();
        scoreText.setFont(Font.font(null,FontWeight.BOLD,32));
        scoreText.setFill(Color.WHITE);
        scoreText.setTextAlignment(TextAlignment.JUSTIFY);
        scoreText.setText("Comrades: "+money);
        scoreText.setX(100);
        scoreText.setY(125);

        Text costText = new Text();
        costText.setFont(Font.font(null,FontWeight.BOLD,20));
        costText.setFill(Color.WHITE);
        costText.setTextAlignment(TextAlignment.JUSTIFY);
        costText.setText(upgradeCost+"c");
        costText.setX(50);
        costText.setY(590);

        Text multiText = new Text();
        multiText.setFont(Font.font(null,FontWeight.BOLD,20));
        multiText.setFill(Color.WHITE);
        multiText.setTextAlignment(TextAlignment.JUSTIFY);
        multiText.setText("x"+multiplier);
        multiText.setX(925);
        multiText.setY(50);

        Text endText = new Text();
        endText.setFont(Font.font(null,FontWeight.BOLD,20));
        endText.setFill(Color.WHITE);
        endText.setTextAlignment(TextAlignment.JUSTIFY);
        endText.setText("20000000c");
        endText.setX(800);
        endText.setY(590);

        Group dogRoot = new Group(dogImageView, x2ImageView, endImageView, scoreText, costText, multiText, endText);

        primaryStage.setScene(new Scene(dogRoot, 1000, 800, Color.RED));

        dogImageView.setOnMouseClicked(event -> {
            double temp=multiplier*base;
            totalComrades+=temp;
            money+=temp;
            scoreText.setText("Comrades: "+money);
            if(rotFlag){
                dogImageView.setRotate(10);
                rotFlag = false;
            }
            else{
                dogImageView.setRotate(-10);
                rotFlag = true;
            }
        });
        x2ImageView.setOnMouseClicked(event -> {
            if(money>=upgradeCost) {
                money-=upgradeCost;
                base++;
                multiplier *= 2;
                upgradeCost*=4;
                scoreText.setText("Comrades: "+money);
                costText.setText(upgradeCost+"c");
                multiText.setText("x"+multiplier);
            }
        });
        endImageView.setOnMouseClicked(event -> {
            if(money>=20000000){
                endTime = System.currentTimeMillis();
                long timeTrial = (endTime-startTime)/1000;
                Text winText = new Text();
                winText.setFont(new Font(46));
                winText.setTextAlignment(TextAlignment.JUSTIFY);
                winText.setText("The Red Doge Conquers All!\nConquered in: "+timeTrial+" seconds\nTotal Comrades: "+totalComrades);
                winText.setX(200);
                winText.setY(200);

                primaryStage.setScene(new Scene(new Group(winText),1000,800, Color.RED));
            }
        });

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
