const getters = {
    sidebar: state => state.app.sidebar,
    language: state => state.app.language,
    visitedViews: state => state.tagsViews.visitedViews,
    cachedViews: state => state.tagsViews.cachedViews,
    allRouters: state => state.asyncRouter.allRouters,
    addRouters: state => state.asyncRouter.addRouters,
    errorLogs: state => state.errorLog.logs,
}

export default getters