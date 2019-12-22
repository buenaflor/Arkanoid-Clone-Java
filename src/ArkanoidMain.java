public class ArkanoidMain {

    static final int windowWidth = 800;
    static final int windowHeight = 600;
    static final float ballRadius = 10.f, ballVelocity = 8.f;
    static final float paddleWidth = 60.f, paddleHeight = 20.f, paddleVelocity = 6.f;

    public static void main(String[] args) {
        StdDraw.setCanvasSize(windowWidth, windowHeight);
        StdDraw.setXscale(0, windowWidth);
        StdDraw.setYscale(windowHeight, 0);
        StdDraw.enableDoubleBuffering();

        Ball ball = new Ball(windowWidth / 2, windowHeight / 2, ballRadius, ballVelocity);
        Paddle paddle = new Paddle(windowWidth / 2, windowHeight - 50, paddleWidth, paddleHeight, paddleVelocity);

        boolean escapePressed = StdDraw.isKeyPressed(27);
        while (!escapePressed) {
            StdDraw.clear();

            // Update shapes
            ball.update();
            paddle.update();

            // Draw shapes
            draw(ball.getShape());
            draw(paddle.getShape());

            if (ball.getShape().isIntersecting(paddle.getShape())) {
                ball.setYVelocity(-ballVelocity);
                if (ball.getPosition().x < paddle.getPosition().x) {
                    ball.setXVelocity(-ballVelocity);
                } else {
                    ball.setXVelocity(ballVelocity);
                }
            }

            StdDraw.show();
            StdDraw.pause(20);
        }
    }

    private static void draw(Shape shape) {
        shape.draw();
    }
}
