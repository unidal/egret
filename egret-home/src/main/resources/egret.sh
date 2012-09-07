echo "running egret with $1 $2<br/>"

LIB_DIR=/Users/marsqing/Projects/tmp/egret-lib/
WEBAPP_DIR=/Users/marsqing/Projects/tmp/egret-webapp/
TOMCAT_DIR="/Users/marsqing/Projects/tmp/apache-tomcat-5.5.34/"

function prepare {
	echo "do prepare<br/>"
	rm -rf $LIB_DIR
	git clone ssh://git@192.168.8.22:58422/egretlib $LIB_DIR
	#check if version compatible
	mkdir -p $WEBAPP_DIR
	if [ ! -d "$WEBAPP_DIR/.git" ]
	then
		cd $WEBAPP_DIR
		git init
		git add *
		git commit -m "`date`"
	fi
	exit 0
}

function activate {
	echo "do activate<br/>"
	if [ ! -d $WEBAPP_DIR/WEB-INF/lib ]
	then
		echo "no webapp dir"
		mkdir -p $WEBAPP_DIR/WEB-INF/lib/
	fi
	bash $TOMCAT_DIR/bin/shutdown.sh
	#cp $LIB_DIR/*.jar $WEBAPP_DIR/WEB-INF/lib/
	for jar in `ls $LIB_DIR/*.jar`
	do
		echo "updating `basename $jar`"
		cp $jar $WEBAPP_DIR/WEB-INF/lib/
	done
	cd $WEBAPP_DIR
	git add *
	bash $TOMCAT_DIR/bin/startup.sh
	exit 0
}

function commit {
	echo "do commit<br/>"
	cd $WEBAPP_DIR
	git add *
	git commit -m "`date`"
	rm -rf $LIB_DIR
	#return [webapp.commit]
	exit 0
}

function rollback {
	echo "do rollback<br/>"
	if [ x$2 = "x" ]
	then
		cd $WEBAPP_DIR
		git reset --hard
	else
		git reset --heard $2
	fi
	rm -rf $LIB_DIR/*
	exit 0
}

if [ $1 = "prepare" ]
then
	prepare
elif [ $1 = "activate" ]
then
	activate
elif [ $1 = "commit" ]
then
	commit
elif [ $1 = "rollback" ]
then
	rollback
fi
exit 111



#bar=""
#for((i=10;i<=100;i+=10))
#do
#	bar=$bar"="
#	echo $bar $i%
#	sleep 1
#done