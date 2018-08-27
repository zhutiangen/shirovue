<template>
    <div id="title-bar">
        <div id="title">comstar</div>
        <div id="title-bar-btns">
            <button id="min-btn" @click="minWindow"><i class="fa fa-window-minimize" aria-hidden="true"></i></button>
            <button id="max-btn" @click="maxWindow"><i class="fa fa-window-maximize" aria-hidden="true"></i></button>
            <button id="close-btn"  @click="closeWindow" style="font-size: 17px; margin-right: 5px;"><i class="fa fa-times" aria-hidden="true"></i></button>
        </div>
    </div>
</template>

<script>
const remote = require('electron').remote;
export default {
    name: "top-bar",
    props: {

    },
    data() {
        return{

        }
    },
    methods: {
        minWindow: () => {
            const window = remote.getCurrentWindow();
            window.minimize();
        },
        maxWindow: () =>{
            const window = remote.getCurrentWindow();
            if (!window.isMaximized()) {
                window.maximize();
            } else {
                window.unmaximize();
            }
        },
        closeWindow: () => {
            const window = remote.getCurrentWindow();
            window.close();
        }
    }
}
</script>

<style scoped>
    #title-bar {
        -webkit-app-region: drag;
        height: 32px;
        padding: 0px;
        margin: 0px;
        color: #ADADAD;
        background-color: #2D2D30;
        font-family: sans-serif;
    }

    #title {
        position: fixed;
        top: 0px;
        left: 12px;
        line-height: 32px;
        font-size: 14px;
        font-family: inherit;
    }

    #title-bar-btns {
        -webkit-app-region: no-drag;
        position: fixed;
        top: 1px;
        right: 0px;
    }

    #title-bar-btns button {
        height: 31px;
        width: 32px;
        background-color: transparent;
        border: none;
        color: #F1F1F1;
        font-size: 12px;
    }

    #title-bar-btns button:hover {
        background-color: #3F3F41;
    }
    #content {
        padding: 12px;
        color: #CCCCCC;
    }

    a {
        color: #68217A;
    }
</style>