<template>
	<div class="user-wrapper" :class="theme">
		<!-- <header-notice class="action"/> -->
		<!-- <span class="action">
			<a :class="className" @mouseover="mouseoverC" @mouseout="mouseoutC" href="javascript:;">
				<a-icon type="mail" style="display: inline-block;top:5px;left: 0;position: relative;" />
				<span v-show="messageNum"></span>
				<div class="elBadgeClassD">
					<div class="d1">
						<i class="el-icon-caret-top" style="color:#ebeef5;"></i>
						<i class="el-icon-caret-top" style="position:absolute;top:2px;left:0;color:#fff;"></i>
					</div>
					<p>短信剩余数量为 {{messageNum}} 条</p>
				</div>
			</a>
		</span> -->
		<a-dropdown>
			<span class="action action-full ant-dropdown-link user-dropdown-menu">
				<!-- <a-avatar class="avatar" size="small" :src="getAvatar()"/> -->
				<img class="avatar" style="width: 25px;" src="@/assets/imgs/userH.png" />
				<span v-if="isDesktop()">{{ userInfo().username +" ( "+userInfo().departName+" )" }}</span>
			</span>
			<a-menu slot="overlay" class="user-dropdown-menu-wrapper">
				<a-menu-item key="3" @click="systemSetting">
					<a-icon type="tool" />
					<span>系统设置</span>
				</a-menu-item>
				<a-menu-item key="4" @click="updatePassword">
					<a-icon type="setting" />
					<span>密码修改</span>
				</a-menu-item>
				<a-menu-item key="5" @click="updateCurrentDepart">
					<a-icon type="cluster" />
					<span>切换部门</span>
				</a-menu-item>
				<!-- <a-menu-item key="2" disabled>
          <a-icon type="setting"/>
          <span>测试</span>
        </a-menu-item>
        <a-menu-divider/>
        <a-menu-item key="3">
          <a href="javascript:;" @click="handleLogout">
            <a-icon type="logout"/>
            <span>退出登录</span>
          </a>
        </a-menu-item>-->
			</a-menu>
		</a-dropdown>
		<span class="action">
			<a class="logout_title" href="javascript:;" @click="handleLogout">
				<a-icon type="logout" />
				<span v-if="isDesktop()">&nbsp;退出登录</span>
			</a>
		</span>
		<user-password ref="userPassword"></user-password>
		<depart-select ref="departSelect" :closable="true" title="部门切换"></depart-select>
		<setting-drawer ref="settingDrawer" :closable="true" title="系统设置"></setting-drawer>
	</div>
</template>

<script>
	import { getAction } from '@/api/manage'
	import HeaderNotice from './HeaderNotice'
	import UserPassword from './UserPassword'
	import SettingDrawer from "@/components/setting/SettingDrawer";
	import DepartSelect from './DepartSelect'
	import {
		mapActions,
		mapGetters
	} from 'vuex'
	import {
		mixinDevice
	} from '@/utils/mixin.js'

	export default {
		name: "UserMenu",
		mixins: [mixinDevice],
		components: {
			HeaderNotice,
			UserPassword,
			DepartSelect,
			SettingDrawer
		},
		props: {
			theme: {
				type: String,
				required: false,
				default: 'dark'
			}
		},
		data() {
			return {
				messageNum: 0,
				className: "elBadgeClass"
			};
		},
		created() {
			// getAction("/arc/userInfo/getUserMsgTotal").then((res) => {
				
			// 	if(res.success) {
			// 		this.messageNum = res.result
			// 	}
			// });
			console.log(this.userInfo())
		},
		methods: {
			...mapActions(["Logout"]),
			...mapGetters(["loginName", "avatar", "userInfo"]),
			getAvatar() {
				console.log('url = ' + window._CONFIG['imgDomainURL'] + "/" + this.avatar())
				return window._CONFIG['imgDomainURL'] + "/" + this.avatar()
			},
			handleLogout() {
				const that = this

				this.$confirm({
					title: '提示',
					content: '真的要注销登录吗 ?',
					onOk() {
						return that.Logout({}).then(() => {
							window.location.href = "/amr-view-boss/";
							//window.location.reload()
						}).catch(err => {
							that.$message.error({
								title: '错误',
								description: err.message
							})
						})
					},
					onCancel() {},
				});
			},
			updatePassword() {
				
				let loginName = this.userInfo().loginName
				this.$refs.userPassword.show(loginName)
			},
			updateCurrentDepart() {
				this.$refs.departSelect.show()
			},
			systemSetting() {
				this.$refs.settingDrawer.showDrawer()
			},
			mouseoutC() {
				//alert(5)
				this.className = "elBadgeClass"
			},
			mouseoverC() {
				//alert(5)
				this.className = "elBadgeClass elBadgeClassC"
			},
		}
	}
</script>

<style scoped>
	.logout_title {
		color: inherit;
		text-decoration: none;
		position: relative;
	}

	.elBadgeClass {
		display: inline-block;
		height: 64px;
		font-size: 22px;
		position: relative;
	}

	.elBadgeClass span {
		display: block;
		width: 9px;
		height: 9px;
		border-radius: 10px;
		background: #f56c6c;
		position: absolute;
		top: 16px;
		right: -2px;
	}

	.elBadgeClassC .elBadgeClassD {
		display: block;
	}

	.elBadgeClassD :hover {
		display: block;
	}

	.elBadgeClassD {
		display: none;
		text-align: center;
		width: 180px;
		padding: 0 5px;
		position: absolute;
		left: -70px;
		top: 58px;
		z-index: 999;
		border-radius: 4px;
		border: 1px solid #ebeef5;
		box-shadow: 0 2px 12px 0 rgba(0, 0, 0, .1);
		background-color: #fff;
		font-size: 12px;
		white-space: normal;
		word-break: break-all;
		word-wrap: break-word;
		color: #000000;
		height: 48px;
	}
</style>
