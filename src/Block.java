public class Block {
    private RectangleShape shape = new RectangleShape();

    public boolean isDestroyed = false;

    public Block (float x, float y, float halfWidth, float halfHeight) {
        this.shape.setFillColor(StdDraw.BOOK_LIGHT_BLUE);
        this.shape.setPosition(x, y);
        this.shape.setSize(halfWidth, halfHeight);
    }

    public RectangleShape getShape() {
        return shape;
    }
}
