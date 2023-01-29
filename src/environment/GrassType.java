package environment;

public enum GrassType {
    TOP_LEFT (0, 0, 0),
    TOP (1, 1, 0),
    TOP_RIGHT (2, 2, 0),
    LEFT (3, 0, 1),
    MIDDLE (4, 1, 1),
    RIGHT (5, 2, 1),
    BOTTOM_LEFT (6, 0, 2),
    BOTTOM (7, 1, 2),
    BOTTOM_RIGHT (8, 2, 2),
    ISLAND_VERTICAL_TOP (9, 3, 0),
    ISLAND_VERTICAL_MIDDLE (10, 3, 1),
    ISLAND_VERTICAL_BOTTOM (11, 3, 2),
    ISLAND_HORIZONTAL_LEFT (12, 0, 3),
    ISLAND_HORIZONTAL_MIDDLE (13, 1, 3),
    ISLAND_HORIZONTAL_RIGHT (14, 2, 3),
    ISLAND_SINGLE (15, 3, 3);

    private final int label;
    private final int x, y;

    GrassType(int label, int x, int y) {
        this.label = label;
        this.x = x;
        this.y = y;
    }

    public int getLabel() {
        return label;
    }

    public int[] getCoordinates() {
        int[] coord = new int[2];
        coord[0] = x;
        coord[1] = y;

        return coord;
    }
}
