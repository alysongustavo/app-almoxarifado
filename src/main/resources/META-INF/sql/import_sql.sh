#!/bin/sh
echo "ENTER DATA BASE NAME:"
read dbname
echo "ENTER DATABASE USER NAME:"
read dbuser
echo "ENTER DATABASE PASSWORD:"
read dbpassword
mysql -u $dbuser -p$dbpassword $dbname<$dbname".sql"
