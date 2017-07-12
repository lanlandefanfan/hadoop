#!/bin/bash

. x.sh

############# PATH #############
root="hdfs://localhost:9000/user/hadoop/file"
JAR_PATH="/home/hadoop/Desktop/bigDataSystem1.4.jar"
PACKAGE_PATH="com.edu.sdu.runner"
################################


# ActiveDevicePlayDay:
#	args[0]: startfile("")
#	args[1]: endfile("")
#	args[2]: ${root}/usr
#	args[3]: ${root}/ActiveDevicePlayDay
ActiveDevicePlayDay(){
  echo "ActiveDevicePlayDay starting..."
  hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.ActiveDevicePlayDay ${startfile_ActiveDevicePlayDay} ${endfile_ActiveDevicePlayDay} ${root}/usr ${root}/ActiveDevicePlayDay
  echo "ActiveDevicePlayDay done."
}


# ActiveUserPlayDay:
#	args[0]: startfile("")
#	args[1]: endfile("")
#	args[2]: ${root}/usr
#	args[3]: ${root}/ActiveUserPlayDay
ActiveUserPlayDay(){
  echo "ActiveUserPlayDay starting..."
  hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.ActiveUserPlayDay ${startfile_ActiveUserPlayDay} ${endfile_ActiveUserPlayDay} ${root}/usr ${root}/ActiveUserPlayDay
  echo "ActiveUserPlayDay done."
}


# AreaCount:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_}
#	args[3]: ${root}/AreaCount
AreaCount(){
  echo "AreaCount starting..."
  hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.AreaCount ${startfile_AreaCount} ${endfile_AreaCount} ${root}/usr/${startfile_AreaCount} ${root}/AreaCount 
  echo "AreaCount done."
}


# DailyActiveDevice:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_}
#	args[3]: ${root}/DailyActiveDeviceCache
#	args[4]: ${root}/DailyActiveDevice
DailyActiveDevice(){
  echo "DailyActiveDevice starting..."
  hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.DailyActiveDevice ${startfile_DailyActiveDevice} ${endfile_DailyActiveDevice} ${root}/usr/${startfile_DailyActiveDevice} ${root}/DailyActiveDeviceCache ${root}/DailyActiveDevice
  echo "DailyActiveDevice done."
}


# DailyActiveUser:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_}
#	args[3]: ${root}/DailyActiveUserCache
#	args[4]: ${root}/DailyActiveUser
DailyActiveUser(){
  echo "DailyActiveUser starting..."
  hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.DailyActiveUser ${startfile_DailyActiveUser} ${endfile_DailyActiveUser} ${root}/usr/${startfile_DailyActiveUser} ${root}/DailyActiveUserCache ${root}/DailyActiveUser
  echo "DailyActiveUser done."
}


# DailyNewDevice:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_}
#	args[3]: ${root}/DailyNewDevice
DailyNewDevice(){
  echo "DailyNewDevice starting..."
  hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.DailyNewDevice ${startfile_DailyNewDevice} ${endfile_DailyNewDevice} ${root}/usr/${startfile_DailyNewDevice} ${root}/DailyNewDevice
  echo "DailyNewDevice done."
}


# DailyNewUser:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_}
#	args[3]: ${root}/DailyNewUser
DailyNewUser(){
  echo "DailyNewUser starting..."
  hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.DailyNewUser ${startfile_DailyNewUser} ${endfile_DailyNewUser} ${root}/usr/${startfile_DailyNewUser} ${root}/DailyNewUser
  echo "DailyNewUser done."
}


# NewDevice:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_}
#	args[3]: ${root}/NewDevice
NewDevice(){
  echo "NewDevice starting..."
  hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.NewDevice ${startfile_NewDevice} ${endfile_NewDevice} ${root}/usr/${startfile_NewDevice} ${root}/NewDevice
  echo "NewDevice done."
}


# NewUser:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_}
#	args[3]: ${root}/NewUser
NewUser(){
  echo "NewUser starting..."
  hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.NewUser ${startfile_NewUser} ${endfile_NewUser} ${root}/usr/${startfile_NewUser} ${root}/NewUser
  echo "NewUser done."
}


# PayMoneyCount:	
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_}
#	args[3]: ${root}/PayMoneyCount
PayMoneyCount(){
  echo "PayMoneyCount starting..."
hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.PayMoneyCount ${startfile_PayMoneyCount} ${endfile_PayMoneyCount} ${root}/usr/${startfile_PayMoneyCount} ${root}/PayMoneyCount
  echo "PayMoneyCount done."
}


# PayUserCount:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_}
#	args[3]: ${root}/PayUserCount
PayUserCount(){
  echo "PayUserCount starting..."
  hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.PayUserCount ${startfile_PayUserCount} ${endfile_PayUserCount} ${root}/usr/${startfile_PayUserCount} ${root}/PayUserCount
  echo "PayUserCount done."
}


# RemainDevice:
#	args[0]: startfile
#	args[1]: endfile
#	args[2]: ${root}/usr/${startfile_}
#	args[3]: ${root}/usr/endfile
#	args[4]: ${root}/RemainDevice
RemainDevice(){
  echo "RemainDevice starting..."
  hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.RemainDevice ${startfile_RemainDevice} ${endfile_RemainDevice} ${root}/usr/${startfile_RemainDevice} ${root}/usr/${endfile_RemainDevice} ${root}/RemainDevice
  echo "RemainDevice done."
}


# RemainUser:
#	args[0]: startfile
#	args[1]: endfile
#	args[2]: ${root}/usr/${startfile_}
#	args[3]: ${root}/usr/endfile
#	args[4]: ${root}/RemainUser
RemainUser(){
  echo "RemainUser starting..."
  hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.RemainUser ${startfile_RemainUser} ${endfile_RemainUser} ${root}/usr/${startfile_RemainUser} ${root}/usr/${endfile_RemainUser} ${root}/RemainUser
  echo "RemainUser done."
}

############ PayUserDetail ##############

# PayUserCI:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_PayUserCI}
#	args[3]: ${root}/PayUserCI
PayUserCI(){
  echo "PayUserCI starting..."
  hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.payUserDetail.PayUserCI ${startfile_PayUserCI} ${endfile_PayUserCI} ${root}/usr/${startfile_PayUserCI} ${root}/PayUserCI
  echo "PayUserCI done."
}
	
# PayUserCO:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_PayUserCO}
#	args[3]: ${root}/PayUserCO

PayUserCO(){
  echo "PayUserCO starting..."
  hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.payUserDetail.PayUserCO ${startfile_PayUserCO} ${endfile_PayUserCO} ${root}/usr/${startfile_PayUserCO} ${root}/PayUserCO
  echo "PayUserCO done."
}
	
# PayUserOS:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_PayUserOS}
#	args[3]: ${root}/PayUserOS
PayUserOS(){
  echo "PayUserOS starting..."
  hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.payUserDetail.PayUserOS ${startfile_PayUserOS} ${endfile_PayUserOS} ${root}/usr/${startfile_PayUserOS} ${root}/PayUserOS
  echo "PayUserOS done."
}
	
# PayUserPT:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_PayUserPT}
#	args[3]: ${root}/PayUserPT
PayUserPT(){
  echo "PayUserPT starting..."
  hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.payUserDetail.PayUserPT ${startfile_PayUserPT} ${endfile_PayUserPT} ${root}/usr/${startfile_PayUserPT} ${root}/PayUserPT
  echo "PayUserPT done."
}

############ PayDeviceDetail ############

# PayDeviceCI:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_PayDeviceCI}
#	args[3]: ${root}/PayDeviceCI
PayDeviceCI(){
  echo "PayDeviceCI starting..."
  hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.payDeviceDetail.PayDeviceCI ${startfile_PayDeviceCI} ${endfile_PayDeviceCI} ${root}/usr/${startfile_PayDeviceCI} ${root}/PayDeviceCI
  echo "PayDeviceCI done."
}
	
# PayDeviceCO:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_PayDeviceCO}
#	args[3]: ${root}/PayDeviceCO
PayDeviceCO(){
  echo "PayDeviceCO starting..."
  hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.payDeviceDetail.PayDeviceCO ${startfile_PayDeviceCO} ${endfile_PayDeviceCO} ${root}/usr/${startfile_PayDeviceCO} ${root}/PayDeviceCO
  echo "PayDeviceCO done."
}
	
# PayDeviceOS:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_PayDeviceOS}
#	args[3]: ${root}/PayDeviceOS
PayDeviceOS(){
  echo "PayDeviceOS starting..."
  hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.payDeviceDetail.PayDeviceOS ${startfile_PayDeviceOS} ${endfile_PayDeviceOS} ${root}/usr/${startfile_PayDeviceOS} ${root}/PayDeviceOS
  echo "PayDeviceOS done."
}

# PayDevicePT:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_PayDevicePT}
#	args[3]: ${root}/PayDevicePT
PayDevicePT(){
  echo "PayDevicePT starting..."
  hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.payDeviceDetail.PayDevicePT ${startfile_PayDevicePT} ${endfile_PayDevicePT} ${root}/usr/${startfile_PayDevicePT} ${root}/PayDevicePT
  echo "PayDevicePT done."
}


############ NewUserDetail ##############

# NewUserCI:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_NewUserCI}
#	args[3]: ${root}/NewUserCI
NewUserCI(){
  echo "NewUserCI starting..."
  hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.newUserDetail.NewUserCI ${startfile_NewUserCI} ${endfile_NewUserCI} ${root}/usr/${startfile_NewUserCI} ${root}/NewUserCI
  echo "NewUserCI done."
}
	
# NewUserCO:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_NewUserCO}
#	args[3]: ${root}/NewUserCO
NewUserCO(){
  echo "NewUserCO starting..."
  hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.newUserDetail.NewUserCO ${startfile_NewUserCO} ${endfile_NewUserCO} ${root}/usr/${startfile_NewUserCO} ${root}/NewUserCO
  echo "NewUserCO done."
}
	
# NewUserOS:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_NewUserOS}
#	args[3]: ${root}/NewUserOS
NewUserOS(){
  echo "NewUserOS starting..."
  hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.newUserDetail.NewUserCO ${startfile_NewUserOS} ${endfile_NewUserOS} ${root}/usr/${startfile_NewUserOS} ${root}/NewUserOS
  echo "NewUserOS done."
}
	
# NewUserPT:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_NewUserPT}
#	args[3]: ${root}/NewUserPT
NewUserPT(){
  echo "NewUserPT starting..."
  hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.newUserDetail.NewUserPT ${startfile_NewUserPT} ${endfile_NewUserPT} ${root}/usr/${startfile_NewUserPT} ${root}/NewUserPT
  echo "NewUserPT done."
}

############ NewDeviceDetail ############

# NewDeviceCI:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_NewDeviceCI}
#	args[3]: ${root}/NewDeviceCI
NewDeviceCI(){
  echo "NewDeviceCI starting..."
  hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.newDeviceDetail.NewDeviceCI ${startfile_NewDeviceCI} ${endfile_NewDeviceCI} ${root}/usr/${startfile_NewDeviceCI} ${root}/NewDeviceCI
  echo "NewDeviceCI done."
}

	
# NewDeviceOS:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_NewDeviceOS}
#	args[3]: ${root}/NewDeviceOS
NewDeviceOS(){
  echo "NewDeviceOS starting..."
  hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.newDeviceDetail.NewDeviceOS ${startfile_NewDeviceOS} ${endfile_NewDeviceOS} ${root}/usr/${startfile_NewDeviceOS} ${root}/NewDeviceOS
  echo "NewDeviceOS done."
}

	
# NewDevicePT:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_NewDevicePT}
#	args[3]: ${root}/NewDevicePT
NewDevicePT(){
  echo "NewDevicePT starting..."
  hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.newDeviceDetail.NewDevicePT ${startfile_NewDevicePT} ${endfile_NewDevicePT} ${root}/usr/${startfile_NewDevicePT} ${root}/NewDevicePT
  echo "NewDevicePT done."
}

############ ActiveUserDetail ###########


# ActiveUserCI:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_ActiveUserCI}
#	args[3]: ${root}/ActiveUserCI
ActiveUserCI(){
  echo "ActiveUserCI starting..."
  hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.activeUserDetail.ActiveUserCI ${startfile_ActiveUserCI} ${endfile_ActiveUserCI} ${root}/usr/${startfile_ActiveUserCI} ${root}/ActiveUserCI
  echo "ActiveUserCI done."
}


# ActiveUserCO:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_ActiveUserCO}
#	args[3]: ${root}/ActiveUserCO
ActiveUserCO(){
  echo "ActiveUserCO starting..."
  hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.activeUserDetail.ActiveUserCO ${startfile_ActiveUserCO} ${endfile_ActiveUserCO} ${root}/usr/${startfile_ActiveUserCO} ${root}/ActiveUserCO
  echo "ActiveUserCO done."
}


# ActiveUserOS:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_ActiveUserOS}
#	args[3]: ${root}/ActiveUserOS
ActiveUserOS(){
  echo "ActiveUserOS starting..."
  hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.activeUserDetail.ActiveUserOS ${startfile_ActiveUserOS} ${endfile_ActiveUserOS} ${root}/usr/${startfile_ActiveUserOS} ${root}/ActiveUserOS
  echo "ActiveUserOS done."
}


# ActiveUserPT:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_ActiveUserPT}
#	args[3]: ${root}/ActiveUserPT
ActiveUserPT(){
  echo "ActiveUserPT starting..."
  hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.activeUserDetail.ActiveUserPT ${startfile_ActiveUserPT} ${endfile_ActiveUserPT} ${root}/usr/${startfile_ActiveUserPT} ${root}/ActiveUserPT
  echo "ActiveUserPT done."
}


############ ActiveDeviceDetail #########

# ActiveDeviceCI:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_ActiveDeviceCI}
#	args[3]: ${root}/ActiveDeviceCI
ActiveDeviceCI(){
  echo "ActiveDeviceCI starting..."
  hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.activeDeviceDetail.ActiveDeviceCI ${startfile_ActiveDeviceCI} ${endfile_ActiveDeviceCI} ${root}/usr/${startfile_ActiveDeviceCI} ${root}/ActiveDeviceCI
  echo "ActiveDeviceCI done."
}


# ActiveDeviceCO:	
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_ActiveDeviceCO}
#	args[3]: ${root}/ActiveDeviceCO
ActiveDeviceCO(){
  echo "ActiveDeviceCO starting..."
  hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.activeDeviceDetail.ActiveDeviceCO ${startfile_ActiveDeviceCO} ${endfile_ActiveDeviceCO} ${root}/usr/${startfile_ActiveDeviceCO} ${root}/ActiveDeviceCO
  echo "ActiveDeviceCO done."
}


# ActiveDeviceOS:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_ActiveDeviceOS}
#	args[3]: ${root}/ActiveDeviceOS
ActiveDeviceOS(){
  echo "ActiveDeviceOS starting..."
  hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.activeDeviceDetail.ActiveDeviceOS ${startfile_ActiveDeviceOS} ${endfile_ActiveDeviceOS} ${root}/usr/${startfile_ActiveDeviceOS} ${root}/ActiveDeviceOS
  echo "ActiveDeviceOS done."
}


# ActiveDevicePT:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_ActiveDevicePT}
#	args[3]: ${root}/ActiveDevicePT
ActiveDevicePT(){
  echo "ActiveDevicePT starting..."
  hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.activeDeviceDetail.ActiveDevicePT ${startfile_ActiveDevicePT} ${endfile_ActiveDevicePT} ${root}/usr/${startfile_ActiveDevicePT} ${root}/ActiveDevicePT
  echo "ActiveDevicePT done."
}

