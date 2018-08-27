import request from '@/utils/request'

const login = {
    login: (data) => {
        return new request({
            url: '/console/login',
            method: 'post',
            data
        })
    },
    logout: () => {
        return new request({
            url: '/console/logout',
            method: 'get',
        })
    },
    register: (data) => {
        return new request({
            url: '/console/register',
            method: 'post',
            data
        })
    }
}

export default login