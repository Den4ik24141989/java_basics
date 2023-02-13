import java.util.concurrent.CopyOnWriteArrayList;

public class NodeUrl {
    private volatile NodeUrl parent;
    private volatile int depth;
    private String urlPage;
    private volatile CopyOnWriteArrayList<NodeUrl> children;

    public NodeUrl(String urlPage) {
        depth = 0;
        this.urlPage = urlPage;
        parent = null;
        children = new CopyOnWriteArrayList<>();
    }

    private int calculateDepth() {
        int result = 0;
        if (parent == null) {
            return result;
        }
        result = 1 + parent.calculateDepth();
        return result;
    }

    public synchronized void addChild(NodeUrl element) {
        NodeUrl root = getRootElement();
        if (!root.contains(element.getUrl())) {
            element.setParent(this);
            children.add(element);
        }
    }

    private boolean contains(String url) {
        if (this.urlPage.equals(url)) {
            return true;
        }
        for (NodeUrl child : children) {
            if (child.contains(url))
                return true;
        }

        return false;
    }

    public String getUrl() {
        return urlPage;
    }

    private void setParent(NodeUrl nodeUrl) {
        synchronized (this) {
            this.parent = nodeUrl;
            this.depth = calculateDepth();
        }
    }

    public NodeUrl getRootElement() {
        return parent == null ? this : parent.getRootElement();
    }

    public CopyOnWriteArrayList<NodeUrl> getChildren() {
        return children;
    }
}
