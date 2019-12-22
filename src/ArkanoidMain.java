import java.util.ArrayList;
import java.util.List;

public class ArkanoidMain {

    static final int windowWidth = 800;
    static final int windowHeight = 600;
    static final float ballRadius = 10.f, ballVelocity = 8.f;
    static final float paddleWidth = 60.f, paddleHeight = 20.f, paddleVelocity = 6.f;
    static final float blockWidth = 60.f, blockHeight = 20.f;
    static final int numOfBlocksX = 11, numOfBlocksY = 4;

    public static void main(String[] args) {
        StdDraw.setCanvasSize(windowWidth, windowHeight);
        StdDraw.setXscale(0, windowWidth);
        StdDraw.setYscale(windowHeight, 0);
        StdDraw.enableDoubleBuffering();

        Ball ball = new Ball(windowWidth / 2, windowHeight / 2, ballRadius, ballVelocity);
        Paddle paddle = new Paddle(windowWidth / 2, windowHeight - 50, paddleWidth, paddleHeight, paddleVelocity);

        List<Block> blocks = new ArrayList<>();
        for (int i = 0; i < numOfBlocksX; i++) {
            for (int j = 0; j < numOfBlocksY; j++) {
                blocks.add(new Block(
                        (i + 1) * (blockWidth + 3) + 22,
                        (j + 2) * (blockHeight + 3),
                        blockWidth / 2,
                        blockHeight / 2)
                );
            }
        }

        while (true) {
            // Hold "Escape" to pause the game
            if (StdDraw.isKeyPressed(27)) continue;

            StdDraw.clear();

            // Update shapes
            ball.update();
            paddle.update();

            if (ball.getShape().isIntersecting(paddle.getShape())) {
                handleCollision(ball, paddle);
            }

            // Test for collision for every block but this can definitely be improved
            for (Block block: blocks) {
                if (ball.getShape().isIntersecting(block.getShape())) {
                    handleCollision(ball, block);
                }
            }

            blocks.removeIf(block -> (block.isDestroyed));

            // Draw shapes
            draw(ball.getShape());
            draw(paddle.getShape());
            for (Block block: blocks) {
                draw(block.getShape());
            }

            StdDraw.show();
            StdDraw.pause(20);
        }
    }

    private static void handleCollision(Ball ball, Paddle paddle) {
        ball.setYVelocity(-ballVelocity);
        if (ball.getPosition().x < paddle.getPosition().x) {
            ball.setXVelocity(-ballVelocity);
        } else {
            ball.setXVelocity(ballVelocity);
        }
    }

    private static void handleCollision(Ball ball, Block block) {
        block.isDestroyed = true;
        float overlapLeft = ball.getShape().right() - block.getShape().left();
        float overlapRight = block.getShape().right() - ball.getShape().left();
        float overlapTop = ball.getShape().bottom() - block.getShape().top();
        float overlapBottom = block.getShape().bottom() - ball.getShape().top();

        boolean ballFromLeft = (Math.abs(overlapLeft) < Math.abs(overlapRight));
        boolean ballFromTop = (Math.abs(overlapTop) < Math.abs(overlapBottom));

        float minOverlapX = ballFromLeft ? overlapLeft : overlapRight;
        float minOverlapY = ballFromTop ? overlapTop : overlapBottom;

        if (Math.abs(minOverlapX) < Math.abs(minOverlapY)) {
            ball.setXVelocity(ballFromLeft ? -ballVelocity : ballVelocity);
        } else {
            ball.setYVelocity(ballFromTop ? -ballVelocity : ballVelocity);
        }
    }

    private static void draw(Shape shape) {
        shape.draw();
    }
}
