package com.mb.stats

class TeamParamSanitizerService {

    def grailsApplication
    def numberService

    void sanitizePaginationParams(Map params) {

        params.offset = numberService.applyDefault(params.offset, config.offset.defaultValue)
        params.offset = Math.max(params.offset, config.offset.defaultValue)


        params.limit = numberService.applyDefault(params.limit, config.limit.defaultValue)
        params.limit = numberService.constrain(params.limit, config.limit.min, config.limit.max)


        params.sort = params.sort ?: config.sort.defaultValue
        params.sort = config.sort.options.contains(params.sort) ? params.sort : config.sort.defaultValue

        params.order = config.order.options.contains(params.order) ? params.order : config.order.defaultValue
    }


    private def getConfig() {
        grailsApplication.config.stats.teams
    }

}
