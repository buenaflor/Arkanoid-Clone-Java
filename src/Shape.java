import java.awt.*;

public abstract class Shape {
    private Vector2 position = new Vector2();

    private Color color;

    public void setPosition(float x, float y) {
        position.x = x;
        position.y = y;
    }

    public Vector2 getPosition() { return position; }

    public void setFillColor(Color color) {
        this.color = color;
        StdDraw.setPenColor(color);
    }

    public void move(Vector2 velocity) {
        position.x += velocity.x;
        position.y += velocity.y;
    }

    public Color getColor() {
        return color;
    }

    public abstract void draw();
    public abstract float right();
    public abstract float left();
    public abstract float top();
    public abstract float bottom();

    public boolean isIntersecting(Shape other) {
        return this.right() >= other.left() && this.left() <= other.right() && this.bottom() >= other.top() && this.top() <= other.bottom();
    }
}

class RectangleShape extends Shape {
    public float halfWidth;
    public float halfHeight;

    public void setSize(float halfWidth, float halfHeight) {
        this.halfWidth = halfWidth;
        this.halfHeight = halfHeight;
    }

    @Override
    public void draw() {
        StdDraw.setPenColor(getColor());
        StdDraw.filledRectangle(getPosition().x, getPosition().y, halfWidth, halfHeight);
    }

    @Override
    public float right() {
        return getPosition().x + halfWidth;
    }

    @Override
    public float left() {
        return getPosition().x - halfWidth;
    }

    @Override
    public float top() {
        return getPosition().y - halfHeight;
    }

    @Override
    public float bottom() {
        return getPosition().y + halfHeight;
    }
}

class CircleShape extends Shape {
    private float radius;

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getRadius() {
        return this.radius;
    }

    @Override
    public void draw() {
        StdDraw.setPenColor(getColor());
        StdDraw.filledCircle(getPosition().x, getPosition().y, radius);
    }

    @Override
    public float right() {
        return getPosition().x + radius;
    }

    @Override
    public float left() {
        return getPosition().x - radius;
    }

    @Override
    public float top() {
        return getPosition().y - radius;
    }

    @Override
    public float bottom() {
        return getPosition().y + radius;
    }
}