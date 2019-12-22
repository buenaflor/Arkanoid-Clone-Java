public class Paddle {
    private RectangleShape shape = new RectangleShape();
    private Vector2 velocityVect = new Vector2(0, 0);
    private float velocity;

    public Paddle(int x, int y, float paddleWidth, float paddleHeight, float paddleVelocity) {
        this.shape.setSize(paddleWidth / 2, paddleHeight / 2);
        this.shape.setPosition(x, y);
        this.shape.setFillColor(StdDraw.BLACK);
        this.velocity = paddleVelocity;
    }

    public void update() {
        shape.move(velocityVect);

        if (StdDraw.isKeyPressed(37) && shape.left() > 0) { velocityVect.x = -velocity; }
        else if (StdDraw.isKeyPressed(39) && shape.right() < ArkanoidMain.windowWidth) { velocityVect.x = velocity; }
        else velocityVect.x = 0;
    }

    public RectangleShape getShape() {
        return shape;
    }

    public Vector2 getPosition() {
        return shape.getPosition();
    }
}
