class Solution {
        int[] parent, depth, size, heavy, head, pos;
    int timer;
    List<Integer>[] graph;
    SegTree seg;
    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries){
        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        parent = new int[n];
        depth = new int[n];
        size = new int[n];
        heavy = new int[n];
        head = new int[n];
        pos = new int[n];

        Arrays.fill(parent, -2);
        Arrays.fill(heavy, -1);

        // Build parent and depth arrays iteratively
        int[] order = new int[n];
        int[] stack = new int[n];
        int top = 0, count = 0;

        stack[top++] = 0;
        parent[0] = -1;

        while (top > 0) {
            int u = stack[--top];
            order[count++] = u;

            for (int v : graph[u]) {
                if (v == parent[u]) continue;

                parent[v] = u;
                depth[v] = depth[u] + 1;
                stack[top++] = v;
            }
        }

        // Calculate subtree sizes and heavy children
        for (int i = n - 1; i >= 0; i--) {
            int u = order[i];
            size[u] = 1;
            int maxSize = 0;

            for (int v : graph[u]) {
                if (parent[v] != u) continue;

                size[u] += size[v];

                if (size[v] > maxSize) {
                    maxSize = size[v];
                    heavy[u] = v;
                }
            }
        }

        // Heavy-Light Decomposition
        int[] starts = new int[n];
        int[] heads = new int[n];
        int sp = 0;
        timer = 0;

        starts[sp] = 0;
        heads[sp++] = 0;

        while (sp > 0) {
            --sp;
            int start = starts[sp];
            int h = heads[sp];

            for (int u = start; u != -1; u = heavy[u]) {
                head[u] = h;
                pos[u] = timer++;

                for (int v : graph[u]) {
                    if (parent[v] == u && v != heavy[u]) {
                        starts[sp] = v;
                        heads[sp++] = v;
                    }
                }
            }
        }

        // Each character is represented by one bit
        char[] chars = s.toCharArray();
        int[] base = new int[n];

        for (int i = 0; i < n; i++) {
            base[pos[i]] = 1 << (chars[i] - 'a');
        }

        seg = new SegTree(base);
        List<Boolean> ans = new ArrayList<>();

        for (String query : queries) {
            String[] p = query.trim().split("\\s+");

            if (p[0].equals("update")) {
                int u = Integer.parseInt(p[1]);
                char c = p[2].charAt(0);

                chars[u] = c;
                seg.update(pos[u], 1 << (c - 'a'));

            } else {
                int u = Integer.parseInt(p[1]);
                int v = Integer.parseInt(p[2]);

                int mask = 0;

                while (head[u] != head[v]) {
                    if (depth[head[u]] < depth[head[v]]) {
                        int temp = u;
                        u = v;
                        v = temp;
                    }

                    mask ^= seg.query(pos[head[u]], pos[u]);
                    u = parent[head[u]];
                }

                int left = Math.min(pos[u], pos[v]);
                int right = Math.max(pos[u], pos[v]);

                mask ^= seg.query(left, right);

                // At most one character may occur an odd number of times
                ans.add((mask & (mask - 1)) == 0);
            }
        }

        return ans;
    }

    class SegTree {
        int[] tree;
        int n;

        SegTree(int[] arr) {
            n = arr.length;
            tree = new int[4 * n];
            build(1, 0, n - 1, arr);
        }

        void build(int node, int l, int r, int[] arr) {
            if (l == r) {
                tree[node] = arr[l];
                return;
            }

            int mid = (l + r) / 2;
            build(node * 2, l, mid, arr);
            build(node * 2 + 1, mid + 1, r, arr);

            tree[node] = tree[node * 2] ^ tree[node * 2 + 1];
        }

        void update(int index, int value) {
            update(1, 0, n - 1, index, value);
        }

        void update(int node, int l, int r, int index, int value) {
            if (l == r) {
                tree[node] = value;
                return;
            }

            int mid = (l + r) / 2;

            if (index <= mid) {
                update(node * 2, l, mid, index, value);
            } else {
                update(node * 2 + 1, mid + 1, r, index, value);
            }

            tree[node] = tree[node * 2] ^ tree[node * 2 + 1];
        }

        int query(int ql, int qr) {
            return query(1, 0, n - 1, ql, qr);
        }

        int query(int node, int l, int r, int ql, int qr) {
            if (ql <= l && r <= qr) return tree[node];

            if (r < ql || l > qr) return 0;

            int mid = (l + r) / 2;

            return query(node * 2, l, mid, ql, qr)
                 ^ query(node * 2 + 1, mid + 1, r, ql, qr);
        }
    }
}
