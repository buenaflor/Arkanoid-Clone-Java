public class Ball {
    private float velocity;
    private Vector2 velocityVect;
    private CircleShape shape = new CircleShape();

    public Ball (int x, int y, float ballRadius, float ballVelocity) {
        this.shape.setPosition(x, y);
        this.shape.setRadius(ballRadius);
        this.shape.setFillColor(StdDraw.BLACK);
        this.velocity = ballVelocity;
        this.velocityVect = new Vector2(-ballVelocity, -ballVelocity);   // Initial velocity
    }

    public void setYVelocity(float yVelocity) {
        this.velocityVect.y = yVelocity;
    }

    public void setXVelocity(float xVelocity) {
        this.velocityVect.x = xVelocity;
    }

    public void update() {
        shape.move(velocityVect);

        if (shape.top() < 0) velocityVect.y = velocity;
        else if (shape.bottom() > ArkanoidMain.windowHeight) velocityVect.y = -velocity;

        if (shape.left() < 0) velocityVect.x = velocity;
        else if (shape.right() > ArkanoidMain.windowWidth) velocityVect.x = -velocity;
    }

    public CircleShape getShape() {
        return shape;
    }

    public Vector2 getPosition() {
        return shape.getPosition();
    }
}
