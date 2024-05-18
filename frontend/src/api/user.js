//导入request.js请求工具
import request from '@/utils/request.js'

//提供调用注册接口的函数
export const userRegisterService = (registerData)=>{
    //借助于UrlSearchParams完成传递
    const params = new URLSearchParams()
    for(let key in registerData){
        params.append(key,registerData[key]);
    }
    return request.post('/user/register',params);
}

//提供调用登录接口的函数
export const userLoginService = (loginData)=>{
    const params = new URLSearchParams();
    for(let key in loginData){
        params.append(key,loginData[key])
    }
    return request.post('/user/login',params)
}


//获取用户详细信息
export const userInfoService = ()=>{
    return request.get('/user/userInfo')
}

//修改个人信息
export const userInfoUpdateService = (userInfoData)=>{
   return request.put('/user/update',userInfoData)
}

//修改头像
export const userAvatarUpdateService = (avatarUrl)=>{
    const urlSearchParams = new URLSearchParams();
    urlSearchParams.append('avatarUrl',avatarUrl)
    return request.patch('/user/updateAvatar',urlSearchParams)
}

//修改密码
export const userPwdUpdateService = (pwdData) => {
    return request.patch('/user/updatePwd', pwdData)
  }

// export function displayAvatar(currentID) {
//     // 发送请求获取头像文件
//     fetch(`/api/avatar?id=${currentID}`)
//       .then(response => response.blob()) // 将响应转换为 Blob 对象
//       .then(blob => {
//         // 创建一个临时的 URL 用于预览头像文件
//         const imageUrl = URL.createObjectURL(blob);
        
//         // 在页面上显示头像
//         const avatarImage = document.getElementById('avatar-image');
//         avatarImage.src = imageUrl;
//       })
//       .catch(error => {
//         console.error('Error fetching avatar:', error);
//       });
//   }