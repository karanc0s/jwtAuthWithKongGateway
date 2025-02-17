local typedefs = require "kong.db.schema.typedefs"

return {
    name = "plugin_jwt_auth",
    fields = {
        { consumer = typedefs.no_consumer },
        { protocols = typedefs.protocols_http },
        { config = {
            type = "record",
            fields = {
                {
                    auth_service_url = {
                        type = "string",
                        required = true,
                        default = "http://auth-service:8081/auth/validate"
                    }
                },
            },
        },
        },
    },
}