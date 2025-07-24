🌳 Tree Grafting Utility
A simple C program to export, deserialize, and graft one prefix‑formatted binary tree onto another by matching node labels.

🔧 Features
📂 Export (-E):
Read a space‑separated prefix tree from stdin, write it to a .saage file.

🔗 Graft (-G):
Load two .saage trees, find the node in A matching the root of B, and graft a copy of B onto it—then save to result.wise.

🗑️ Cleanup:
Recursive free_tree() to avoid memory leaks.

🛠️ How It Works
Allocate & Free

allocate_node(s): creates a new node with val = s.

free_tree(&T): recursively frees all nodes.

Construct & Parse

construct_tree(&T): reads a line like

css
Copy
Edit
A B 0 0 C 0 0
where 0 = no child, builds the Tree in prefix order.

Serialize / Deserialize

serialize(fn, T): writes pre‑order markers “Value: …”, “LEFT :”, “RIGHT :”

deserialize(fn, &T): reads those markers back into tree nodes

Copy & Graft

copy(&clone, src): deep‑copies entire subtree

expansion(&A, B):

🔍 Search in A for label B->val

🧩 Copy B → copy_B

🔗 Attach copy_B so its children replace the found node’s subtrees

🚀 Usage
bash
Copy
Edit
# 1) Export mode: build from console → write to source.saage
./main -E source.saage
# (enter e.g.) A B 0 0 C 0 0

# 2) Graft mode: graft.saage onto source.saage → result.wise
./main -G source.saage graft.saage

# 3) Inspect result.wise:
cat result.wise
🎯 Example
Create source.saage

bash
Copy
Edit
$ ./main -E source.saage
Enter tree (prefix, “0” = null):  
A B 0 0 D 0 0
This builds:

css
Copy
Edit
    A
   / \
  B   D
Create graft.saage

bash
Copy
Edit
$ ./main -E graft.saage
Enter tree (prefix, “0” = null):  
B X 0 0 Y 0 0
css
Copy
Edit
    B
   / \
  X   Y
Graft

bash
Copy
Edit
$ ./main -G source.saage graft.saage
Grafted graft.saage onto source.saage → result.wise
Result (result.wise):

text
Copy
Edit
Value: A
LEFT :
  Value: B
  LEFT :
    Value: X
    LEFT :
    RIGHT :
  RIGHT :
    Value: Y
    LEFT :
    RIGHT :
RIGHT :
  Value: D
  LEFT :
  RIGHT :
