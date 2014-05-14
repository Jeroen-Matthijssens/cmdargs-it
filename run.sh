# java -cp target/dependency/*:target/* it.tutske.cmdargs.Main "$@"

function command () {
	java -cp target/dependency/*:target/* it.tutske.cmdargs.Main "$@";
}

echo "$ command --message='Hello World!' --print";
command --message='Hello World!' --print;
echo "$ command --message='Hello World!' --no-print";
command --message='Hello World!' --no-print;
echo "$ command --message='Hello World!'";
command --message='Hello World!';
echo "$ command --print --message='Hello World!'";
command --print --message='Hello World!';
echo "$ command --message=Hello,World --print";
command --message=Hello,World --print;
echo "$ command --message=Hello,World --print-all";
command --message=Hello,World --print-all;

echo "--";

echo "$ command -m 'Hello World!' -p";
command -m 'Hello World!' -p;
echo "$ command -m 'Hello World!' -p false";
command -m 'Hello World!' -p false;
echo "$ command -m 'Hello World!'";
command -m 'Hello World!';
echo "$ command -p -m 'Hello World!'";
command -p -m 'Hello World!';
echo "$ command -m Hello -m World -p";
command -m Hello -m World -p;
echo "$ command -m Hello -m World -P";
command -m Hello -m World -P;

echo "--";

echo "$ command --print";
command --print
echo "$ command --message";
command --message
