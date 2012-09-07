cd `dirname $0`
echo "running egret with $1 $2<br/>"

TOMCAT_HOME="/Users/marsqing/Projects/tmp/tomcat/"

if [ x$TOMCAT_HOME = "x" ]
then
	echo "set TOMCAT_HOME first"
	exit 1
fi

LIB_DIR=`pwd`/egret-lib/
TOMCAT_DIR=$TOMCAT_HOME
WEBAPP_DIR="$TOMCAT_DIR/webapps/egret-demo-1.0.0-SNAPSHOT/"

function prepare {
	echo "Start prepare...<br/>"
	rm -rf $LIB_DIR
	echo "Fetch app update from repository...<br/>"
	git clone ssh://git@192.168.8.22:58422/egretlib $LIB_DIR
	echo "Done<br/>"
	#check if version compatible
	mkdir -p $WEBAPP_DIR
	if [ ! -d "$WEBAPP_DIR/.git" ]
	then
		cd $WEBAPP_DIR
		git init > /dev/null
		git add * > /dev/null
		git commit -m "`date`" > /dev/null
	fi
	echo "All done!<br/>"
	exit 0
}

function activate {
	echo "Start activate...<br/>"
	if [ ! -d $WEBAPP_DIR/WEB-INF/lib ]
	then
		mkdir -p $WEBAPP_DIR/WEB-INF/lib/
	fi
	echo "Stopping web server...<br/>"
	pid=`jps -l|grep Bootstrap|awk '{print $1}'`
	kill -9 $pid
	echo "Done<br/>"
	#cp $LIB_DIR/*.jar $WEBAPP_DIR/WEB-INF/lib/
	echo "Replace files...<br/>"
	for jar in `ls $LIB_DIR/*.jar`
	do
		echo "&nbsp;&nbsp;&nbsp;&nbsp;Updating `basename $jar`<br/>"
		cp $jar $WEBAPP_DIR/WEB-INF/lib/
	done
	echo "Done<br/>"
	cd $WEBAPP_DIR
	git add *
	echo "Starting web server...<br/>"
	bash $TOMCAT_DIR/bin/startup.sh
	echo "Done<br/>"
	echo "All done!<br/>"
	exit 0
}

function commit {
	echo "Start commit...<br/>"
	echo "Commit updated files...<br/>"
	cd $WEBAPP_DIR
	git add *
	git commit -m "`date`" | sed -e 's/$/\<br\/\>/'
	echo "Done<br/>"
	echo "Clean temp directories...<br/>"
	rm -rf $LIB_DIR
	#return [webapp.commit]
	echo "Done<br/>"
	echo "All done!<br/>"
	exit 0
}

function rollback {
	echo "Start rollback...<br/>"
	echo "Rollback updated files...<br/>"
	if [ x$2 = "x" ]
	then
		cd $WEBAPP_DIR
		git reset --hard
	else
		git reset --heard $2
	fi
	echo "Done<br/>"
	echo "Clean temp directories...<br/>"
	rm -rf $LIB_DIR/*
	echo "Done<br/>"
	echo "Stopping web server...<br/>"
	pid=`jps -l|grep Bootstrap|awk '{print $1}'`
	kill -9 $pid
	echo "Done<br/>"
	echo "Starting web server...<br/>"
	bash $TOMCAT_DIR/bin/startup.sh
	echo "Done<br/>"
	echo "All done!<br/>"
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
