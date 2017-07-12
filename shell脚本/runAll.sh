#!/bin/bash

############## Path ############

. x.sh

root="hdfs://localhost:9000/user/hadoop/file"
JAR_PATH="/home/hadoop/Desktop/bigDataSystem1.4.jar"
PACKAGE_PATH="com.edu.sdu.runner"



################################
################################

############ PayUserDetail ##############

# PayUserCI:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_PayUserCI}
#	args[3]: ${root}/PayUserCI
hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.payUserDetail.PayUserCI ${startfile_PayUserCI} ${endfile_PayUserCI} ${root}/usr/${startfile_PayUserCI} ${root}/PayUserCI
	
# PayUserCO:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_PayUserCO}
#	args[3]: ${root}/PayUserCO
hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.payUserDetail.PayUserCO ${startfile_PayUserCO} ${endfile_PayUserCO} ${root}/usr/${startfile_PayUserCO} ${root}/PayUserCO

# PayUserOS:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_PayUserOS}
#	args[3]: ${root}/PayUserOS
hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.payUserDetail.PayUserOS ${startfile_PayUserOS} ${endfile_PayUserOS} ${root}/usr/${startfile_PayUserOS} ${root}/PayUserOS

# PayUserPT:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_PayUserPT}
#	args[3]: ${root}/PayUserPT
hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.payUserDetail.PayUserPT ${startfile_PayUserPT} ${endfile_PayUserPT} ${root}/usr/${startfile_PayUserPT} ${root}/PayUserPT

############ PayDeviceDetail ############

# PayDeviceCI:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_PayDeviceCI}
#	args[3]: ${root}/PayDeviceCI
hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.payDeviceDetail.PayDeviceCI ${startfile_PayDeviceCI} ${endfile_PayDeviceCI} ${root}/usr/${startfile_PayDeviceCI} ${root}/PayDeviceCI
# PayDeviceCO:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_PayDeviceCO}
#	args[3]: ${root}/PayDeviceCO
	
hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.payDeviceDetail.PayDeviceCO ${startfile_PayDeviceCO} ${endfile_PayDeviceCO} ${root}/usr/${startfile_PayDeviceCO} ${root}/PayDeviceCO
# PayDeviceOS:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_PayDeviceOS}
#	args[3]: ${root}/PayDeviceOS


hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.payDeviceDetail.PayDeviceOS ${startfile_PayDeviceOS} ${endfile_PayDeviceOS} ${root}/usr/${startfile_PayDeviceOS} ${root}/PayDeviceOS
# PayDevicePT:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_PayDevicePT}
#	args[3]: ${root}/PayDevicePT
hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.payDeviceDetail.PayDevicePT ${startfile_PayDevicePT} ${endfile_PayDevicePT} ${root}/usr/${startfile_PayDevicePT} ${root}/PayDevicePT

############ NewUserDetail ##############

# NewUserCI:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_NewUserCI}
#	args[3]: ${root}/NewUserCI
	
hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.newUserDetail.NewUserCI ${startfile_NewUserCI} ${endfile_NewUserCI} ${root}/usr/${startfile_NewUserCI} ${root}/NewUserCI
# NewUserCO:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_NewUserCO}
#	args[3]: ${root}/NewUserCO
	
hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.newUserDetail.NewUserCO ${startfile_NewUserCO} ${endfile_NewUserCO} ${root}/usr/${startfile_NewUserCO} ${root}/NewUserCO
# NewUserOS:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_NewUserOS}
#	args[3]: ${root}/NewUserOS
hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.newUserDetail.NewUserCO ${startfile_NewUserOS} ${endfile_NewUserOS} ${root}/usr/${startfile_NewUserOS} ${root}/NewUserOS
	
# NewUserPT:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_NewUserPT}
#	args[3]: ${root}/NewUserPT
hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.newUserDetail.NewUserPT ${startfile_NewUserPT} ${endfile_NewUserPT} ${root}/usr/${startfile_NewUserPT} ${root}/NewUserPT

############ NewDeviceDetail ############

# NewDeviceCI:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_NewDeviceCI}
#	args[3]: ${root}/NewDeviceCI
hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.newDeviceDetail.NewDeviceCI ${startfile_NewDeviceCI} ${endfile_NewDeviceCI} ${root}/usr/${startfile_NewDeviceCI} ${root}/NewDeviceCI
	
# NewDeviceOS:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_NewDeviceOS}
#	args[3]: ${root}/NewDeviceOS
hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.newDeviceDetail.NewDeviceOS ${startfile_NewDeviceOS} ${endfile_NewDeviceOS} ${root}/usr/${startfile_NewDeviceOS} ${root}/NewDeviceOS	

# NewDevicePT:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_NewDevicePT}
#	args[3]: ${root}/NewDevicePT

hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.newDeviceDetail.NewDevicePT ${startfile_NewDevicePT} ${endfile_NewDevicePT} ${root}/usr/${startfile_NewDevicePT} ${root}/NewDevicePT
############ ActiveUserDetail ###########


# ActiveUserCI:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_ActiveUserCI}
#	args[3]: ${root}/ActiveUserCI

hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.activeUserDetail.ActiveUserCI ${startfile_ActiveUserCI} ${endfile_ActiveUserCI} ${root}/usr/${startfile_ActiveUserCI} ${root}/ActiveUserCI

# ActiveUserCO:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_ActiveUserCO}
#	args[3]: ${root}/ActiveUserCO
hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.activeUserDetail.ActiveUserCO ${startfile_ActiveUserCO} ${endfile_ActiveUserCO} ${root}/usr/${startfile_ActiveUserCO} ${root}/ActiveUserCO

# ActiveUserOS:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_ActiveUserOS}
#	args[3]: ${root}/ActiveUserOS
hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.activeUserDetail.ActiveUserOS ${startfile_ActiveUserOS} ${endfile_ActiveUserOS} ${root}/usr/${startfile_ActiveUserOS} ${root}/ActiveUserOS

# ActiveUserPT:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_ActiveUserPT}
#	args[3]: ${root}/ActiveUserPT
hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.activeUserDetail.ActiveUserPT ${startfile_ActiveUserPT} ${endfile_ActiveUserPT} ${root}/usr/${startfile_ActiveUserPT} ${root}/ActiveUserPT

############ ActiveDeviceDetail #########

# ActiveDeviceCI:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_ActiveDeviceCI}
#	args[3]: ${root}/ActiveDeviceCI

hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.activeDeviceDetail.ActiveDeviceCI ${startfile_ActiveDeviceCI} ${endfile_ActiveDeviceCI} ${root}/usr/${startfile_ActiveDeviceCI} ${root}/ActiveDeviceCI
# ActiveDeviceCO:	
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_ActiveDeviceCO}
#	args[3]: ${root}/ActiveDeviceCO

hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.activeDeviceDetail.ActiveDeviceCO ${startfile_ActiveDeviceCO} ${endfile_ActiveDeviceCO} ${root}/usr/${startfile_ActiveDeviceCO} ${root}/ActiveDeviceCO
# ActiveDeviceOS:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_ActiveDeviceOS}
#	args[3]: ${root}/ActiveDeviceOS

hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.activeDeviceDetail.ActiveDeviceOS ${startfile_ActiveDeviceOS} ${endfile_ActiveDeviceOS} ${root}/usr/${startfile_ActiveDeviceOS} ${root}/ActiveDeviceOS

# ActiveDevicePT:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_ActiveDevicePT}
#	args[3]: ${root}/ActiveDevicePT
hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.activeDeviceDetail.ActiveDevicePT ${startfile_ActiveDevicePT} ${endfile_ActiveDevicePT} ${root}/usr/${startfile_ActiveDevicePT} ${root}/ActiveDevicePT

################## runner ###############

# 可用*
# ActiveDevicePlayDay:
#	args[0]: startfile("")
#	args[1]: endfile("")
#	args[2]: ${root}/usr
#	args[3]: ${root}/ActiveDevicePlayDay
hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.ActiveDevicePlayDay ${startfile_ActiveDevicePlayDay} ${endfile_ActiveDevicePlayDay} ${root}/usr ${root}/ActiveDevicePlayDay

# 可用*
# ActiveUserPlayDay:
#	args[0]: startfile("")
#	args[1]: endfile("")
#	args[2]: ${root}/usr
#	args[3]: ${root}/ActiveUserPlayDay
hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.ActiveUserPlayDay ${startfile_ActiveUserPlayDay} ${endfile_ActiveUserPlayDay} ${root}/usr ${root}/ActiveUserPlayDay



# 可用*
# AreaCount:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_}
#	args[3]: ${root}/AreaCount
hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.AreaCount ${startfile_AreaCount} ${endfile_AreaCount} ${root}/usr/${startfile_AreaCount} ${root}/AreaCount 


# 可用*
# DailyActiveDevice:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_}
#	args[3]: ${root}/DailyActiveDeviceCache
#	args[4]: ${root}/DailyActiveDevice
hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.DailyActiveDevice ${startfile_DailyActiveDevice} ${endfile_DailyActiveDevice} ${root}/usr/${startfile_DailyActiveDevice} ${root}/DailyActiveDeviceCache ${root}/DailyActiveDevice


# 可用*
# DailyActiveUser:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_}
#	args[3]: ${root}/DailyActiveUserCache
#	args[4]: ${root}/DailyActiveUser
hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.DailyActiveUser ${startfile_DailyActiveUser} ${endfile_DailyActiveUser} ${root}/usr/${startfile_DailyActiveUser} ${root}/DailyActiveUserCache ${root}/DailyActiveUser


# 可用*
# DailyNewDevice:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_}
#	args[3]: ${root}/DailyNewDevice
hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.DailyNewDevice ${startfile_DailyNewDevice} ${endfile_DailyNewDevice} ${root}/usr/${startfile_DailyNewDevice} ${root}/DailyNewDevice


# 可用*
# DailyNewUser:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_}
#	args[3]: ${root}/DailyNewUser
hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.DailyNewUser ${startfile_DailyNewUser} ${endfile_DailyNewUser} ${root}/usr/${startfile_DailyNewUser} ${root}/DailyNewUser


# 可用*
# NewDevice:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_}
#	args[3]: ${root}/NewDevice
hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.NewDevice ${startfile_NewDevice} ${endfile_NewDevice} ${root}/usr/${startfile_NewDevice} ${root}/NewDevice


# 可用*
# NewUser:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_}
#	args[3]: ${root}/NewUser
hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.NewUser ${startfile_NewUser} ${endfile_NewUser} ${root}/usr/${startfile_NewUser} ${root}/NewUser


# 可用*
# PayMoneyCount:	
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_}
#	args[3]: ${root}/PayMoneyCount
hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.PayMoneyCount ${startfile_PayMoneyCount} ${endfile_PayMoneyCount} ${root}/usr/${startfile_PayMoneyCount} ${root}/PayMoneyCount


# 可用*
# PayUserCount:
#	args[0]: startfile
#	args[1]: endfile("")
#	args[2]: ${root}/usr/${startfile_}
#	args[3]: ${root}/PayUserCount
hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.PayUserCount ${startfile_PayUserCount} ${endfile_PayUserCount} ${root}/usr/${startfile_PayUserCount} ${root}/PayUserCount

	
# 可用*
# RemainDevice:
#	args[0]: startfile
#	args[1]: endfile
#	args[2]: ${root}/usr/${startfile_}
#	args[3]: ${root}/usr/endfile
#	args[4]: ${root}/RemainDevice
hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.RemainDevice ${startfile_RemainDevice} ${endfile_RemainDevice} ${root}/usr/${startfile_RemainDevice} ${root}/usr/${endfile_RemainDevice} ${root}/RemainDevice

	
# 可用*
# RemainUser:
#	args[0]: startfile
#	args[1]: endfile
#	args[2]: ${root}/usr/${startfile_}
#	args[3]: ${root}/usr/endfile
#	args[4]: ${root}/RemainUser
hadoop jar ${JAR_PATH} ${PACKAGE_PATH}.RemainUser ${startfile_RemainUser} ${endfile_RemainUser} ${root}/usr/${startfile_RemainUser} ${root}/usr/${endfile_RemainUser} ${root}/RemainUser

