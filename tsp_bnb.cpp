#include <iostream>
#include <vector>
#include <queue>
#include <utility>
#include <cstring>
#include <climits>
using namespace std;
 
// `N` is the total number of total nodes on the graph or cities on the map
#define N 5
 
// Sentinel value for representing `INFINITY`
#define INF INT_MAX
 
// State Space Tree nodes
struct Node
{
    // stores edges of the state-space tree
    // help in tracing the path when the answer is found
    vector<pair<int, int>> path;
 
    // stores the reduced matrix
    int reducedMatrix[N][N];
 
    // stores the lower bound
    int cost;
 
    // stores the current city number
    int vertex;
 
    // stores the total number of cities visited so far
    int level;
};
 
// Function to allocate a new node `(i, j)` corresponds to visiting city `j`
// from city `i`
Node* newNode(int parentMatrix[N][N], vector<pair<int, int>> const &path,
            int level, int i, int j)
{
    Node* node = new Node;
 
    // stores ancestors edges of the state-space tree
    node->path = path;
    // skip for the root node
    if (level != 0)
    {
        // add a current edge to the path
        node->path.push_back(make_pair(i, j));
    }
 
    // copy data from the parent node to the current node
    memcpy(node->reducedMatrix, parentMatrix,
        sizeof node->reducedMatrix);
 
    // Change all entries of row `i` and column `j` to `INFINITY`
    // skip for the root node
    for (int k = 0; level != 0 && k < N; k++)
    {
        // set outgoing edges for the city `i` to `INFINITY`
        node->reducedMatrix[i][k] = INF;
 
        // set incoming edges to city `j` to `INFINITY`
        node->reducedMatrix[k][j] = INF;
    }
 
    // Set `(j, 0)` to `INFINITY`
    // here start node is 0
    node->reducedMatrix[j][0] = INF;
 
    // set number of cities visited so far
    node->level = level;
 
    // assign current city number
    node->vertex = j;
 
    // return node
    return node;
}
 
// Function to reduce each row so that there must be at least one zero in each row
int rowReduction(int reducedMatrix[N][N], int row[N])
{
    // initialize row array to `INFINITY`
    fill_n(row, N, INF);
 
    // `row[i]` contains minimum in row `i`
    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < N; j++)
        {
            if (reducedMatrix[i][j] < row[i]) {
                row[i] = reducedMatrix[i][j];
            }
        }
    }
 
    // reduce the minimum value from each element in each row
    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < N; j++)
        {
            if (reducedMatrix[i][j] != INF && row[i] != INF) {
                reducedMatrix[i][j] -= row[i];
            }
        }
    }
}
 

int columnReduction(int reducedMatrix[N][N], int col[N])
{
    
    fill_n(col, N, INF);
 
    
    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < N; j++)
        {
            if (reducedMatrix[i][j] < col[j]) {
                col[j] = reducedMatrix[i][j];
            }
        }
    }
 
    
    for (int i = 0; i < N; i++)
    {
        for (int j = 0; j < N; j++)
        {
            if (reducedMatrix[i][j] != INF && col[j] != INF) {
                reducedMatrix[i][j] -= col[j];
            }
        }
    }
}
 
// Function to get the lower bound on the path starting at the current minimum node
int calculateCost(int reducedMatrix[N][N])
{
    // initialize cost to 0
    int cost = 0;
 
    // Row Reduction
    int row[N];
    rowReduction(reducedMatrix, row);
 
    // Column Reduction
    int col[N];
    columnReduction(reducedMatrix, col);
 
    // the total expected cost is the sum of all reductions
    for (int i = 0; i < N; i++)
    {
        cost += (row[i] != INT_MAX) ? row[i] : 0,
            cost += (col[i] != INT_MAX) ? col[i] : 0;
    }
 
    return cost;
}
 

void printPath(vector<pair<int, int>> const &list)
{
    for (int i = 0; i < list.size(); i++) {
        cout << list[i].first + 1 << " —> " << list[i].second + 1 << endl;
    }
}
 

struct comp
{
    bool operator()(const Node* lhs, const Node* rhs) const {
        return lhs->cost > rhs->cost;
    }
};
 

int solve(int costMatrix[N][N])
{
    priority_queue<Node*, vector<Node*>, comp> pq;
 
    vector<pair<int, int>> v;
 
    Node* root = newNode(costMatrix, v, 0, -1, 0);
 
    
    root->cost = calculateCost(root->reducedMatrix);
 
    
    pq.push(root);
 
   
    while (!pq.empty())
    {
        
        Node* min = pq.top();
 
       
        pq.pop();
 
        
        int i = min->vertex;
 
       
        if (min->level == N - 1)
        {
           
            min->path.push_back(make_pair(i, 0));
 
            
            printPath(min->path);
 
            
            return min->cost;
        }
 
        
        for (int j = 0; j < N; j++)
        {
            if (min->reducedMatrix[i][j] != INF)
            {
                
                Node* child = newNode(min->reducedMatrix, min->path,
                    min->level + 1, i, j);
 
                child->cost = min->cost + min->reducedMatrix[i][j]
                            + calculateCost(child->reducedMatrix);
 
                
                pq.push(child);
            }
        }
 
        
        delete min;
    }
}
 
int main()
{
   
    
    int costMatrix[N][N] =
    {
        { INF, 20, 17, 15, 25 },
        { 20, INF, 21, 12, 5 },
        {17, 21, INF, 35, 13 },
        { 15, 12, 35, INF, 19 },
        { 25, 5, 13, 19, INF }
    };
 

    
 
   
 
    cout << "\n\nTotal cost is " << solve(costMatrix);
 
    return 0;
}

