local kong = kong
local http = require "resty.http"
local json = require("cjson")

local PluginJwtAuth = {
    PRIORITY = 1450,
    VERSION = "1.0",
}

function PluginJwtAuth:access(config)
    kong.log("reached HERE!!!!")
    -- Use the auth_service_url from the configuration
    local auth_service_url = config.auth_service_url

    kong.log(auth_service_url)

    -- Call auth service
    local httpc = http.new()
    httpc:set_timeouts(10000, 10000, 10000)
    local res, err = httpc:request_uri(auth_service_url, {
        method = "GET",
        headers = {
            ["Authorization"] = kong.request.get_header("Authorization")
        }
    })

    if not res then
        kong.log.err("Failed to call auth service: ", err)
        return kong.response.exit(500, { message = "Internal Server Error" })
    end

    if res.status ~= 200 then
        kong.log.err("Unauthorized")
        return kong.response.exit(res.status, { message = "Unauthorized" })
    end

    -- If authenticated, add user_id to headers and continue
    --local user_id = res.body -- Assuming auth service returns user_id in body

    kong.log(user_id)
    kong.service.request.set_header("X-User-ID", user_id)
    kong.service.request.set_header("Authorization" , kong.request.get_header("Authorization"))
end


return PluginJwtAuth