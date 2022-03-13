import sys; read = sys.stdin.readline
sys.setrecursionlimit(10000)


def makePostOrder(start, end, idx):
    global inOrder, preOrder
    
    if (start == end):
        return str(inOrder[end])
    if (start > end):
        return ""
        
    rootIdx = inOrder.index(preOrder[idx])
    left = makePostOrder(start, rootIdx-1, idx+1)
    right = makePostOrder(rootIdx+1, end, rootIdx+1+idx-start)
    
    return left + right + str(preOrder[idx])



T = int(read())

ans = []
for _ in range(T):
    n = int(read())

    preOrder = list(map(int, read().split()))
    inOrder = list(map(int, read().split()))
    postOrder = makePostOrder(0, n-1, 0)
    ans.append(postOrder.replace('', " ").strip())

print('\n'.join(x for x in ans))
    



