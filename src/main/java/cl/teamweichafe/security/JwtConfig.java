package cl.teamweichafe.security;

public class JwtConfig {

	public static String PRIVATE_KEY = "-----BEGIN RSA PRIVATE KEY-----\n" + 
			"MIIEpAIBAAKCAQEAyO6TlEsaVVWYjtXn3C6b4wEoIBuEVVKaqC9UpetrTZxTRIiT\n" + 
			"s2Xbq/EefBtIwpO2jso7hJqR6/ABws03bQWnWpbs43JjCdEGI/LiJB69k/5wSnYw\n" + 
			"sZR8w0121k+jTpYFwWnCyy0WAMvhGfR+pet/QatDT3a7B3j6UhAxLNwHMI3iZMPc\n" + 
			"xrJJnMoiq5RigHqzafjQ1MSz+syHm4joLe4aUUb7Lo6w2xRYBcwBLNe3ytz9XG6F\n" + 
			"hvErlhY1jOHglnGJqayhPEoiiZQPtMpfqIAUa/WkxzM0EWdcbiQUQZSbvT2rmmOe\n" + 
			"KtHrZmOuuUXfbhf/0maAjqXQdkxhwSrYZO6G6QIDAQABAoIBAEq2s7eTiZIrhtOt\n" + 
			"5E4yIpLxTitKMBXN5mYdW1tqw3aTZz73jfxKJrmzqcM6Hf8EEADeX+050IKrtuvp\n" + 
			"hO+MB6OetS0JzpvK1n6thLsrlxCdrsFxWz/c19uDRWoBKNdC35cArYcBF8pqBSSy\n" + 
			"jhlCDQqtFRVKL9die6niDXcBxcaegsmtY8smto9BGa0y4orpQw9lfy6N8y06TGvH\n" + 
			"z41KN3fZFEwW3E2ShYm1fAqWJMfLXilg7ncLcgvWO+x61jsfC/OsHK8EScZWIAkd\n" + 
			"p0BVhKtkIZF/KQ5Kk+HsQV0Oy6kLVL3fNuas9/KaOQEKCLFt+OJoIlFIMgME+A1v\n" + 
			"ZaEb2xUCgYEA6pU1xjImPPTGdQiPzBxhuMg0Z0nvGD/H4dqF+9aal6GqBCGkcEln\n" + 
			"ltAqHKOxHswjrGUXjQrqeRK9QO5Aay5AwOrm1gHXCCKI2oGYnx2dz5Dv40hKfV3Y\n" + 
			"O10MpEY1X+Ls57sFULf3E9/BpPDtwgBjUTpBS+3F1dSt9af3gf29NkcCgYEA20bc\n" + 
			"MxXm9BsqNj4cLJs1qzzRQQX+0qZMCJ35bE2klM6Dd68qWbweEpcpdtx5xgf/le9l\n" + 
			"mG+m68CvIb4FySztl6sIZIy0iro+4PTm7jrDdRM81bN4GmlaxQlQ+JvOE5v3rwc3\n" + 
			"AVGo8kbKl5wgq+0QMGcIl6qmWu4F4wCCOwLHgU8CgYBUlMQ02DQtWlyMKjAe+kTJ\n" + 
			"6XXroB7TcXvSlYYlzbUj4yh4IOE3k2AEYr4sYWElp5ldOX4KXts4GGegl8cmXiD+\n" + 
			"7jOZCkTMLBeIIZ40EOurwaidHJ2vIj0q+lvqh14LWrjsvOQnvI0b31vk6vHvN1CY\n" + 
			"IxcUF4FzniGzJ4L4ndKxVQKBgQDOiE+fTmHaSjvYG2guG4LRZtcxh5QclMXI2AK2\n" + 
			"H+uEvX7WnAYnvygUGozTh5arD52Me5AVpBR0uWg7P9/zPH4M/OtKybzDbekLuaBJ\n" + 
			"gs/W1efW4NOfDNyR+dRqVVYk4iLImOMWOvQGPudTJZsgVImLDYDqWbGWs9qXlUMU\n" + 
			"kemXwQKBgQCwmBwqjXex/qK0jMfdFVFv78usQwEk/Fqm0fvbiHcm1ZbaGk3ih94t\n" + 
			"4nxCMVZqnQ/0/ZYosd2h5inswAvqN5oywuXkZ0u8y/aAUnVPD79KcuMhd0leHaAK\n" + 
			"tBEGdW0K+XYe8+QeT0TZjvskiWhyWq/5FCBUUHnebjgGNK522LqZtg==\n" + 
			"-----END RSA PRIVATE KEY-----";
	public static String PUBLIC_KEY = "-----BEGIN PUBLIC KEY-----\n" + 
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyO6TlEsaVVWYjtXn3C6b\n" + 
			"4wEoIBuEVVKaqC9UpetrTZxTRIiTs2Xbq/EefBtIwpO2jso7hJqR6/ABws03bQWn\n" + 
			"Wpbs43JjCdEGI/LiJB69k/5wSnYwsZR8w0121k+jTpYFwWnCyy0WAMvhGfR+pet/\n" + 
			"QatDT3a7B3j6UhAxLNwHMI3iZMPcxrJJnMoiq5RigHqzafjQ1MSz+syHm4joLe4a\n" + 
			"UUb7Lo6w2xRYBcwBLNe3ytz9XG6FhvErlhY1jOHglnGJqayhPEoiiZQPtMpfqIAU\n" + 
			"a/WkxzM0EWdcbiQUQZSbvT2rmmOeKtHrZmOuuUXfbhf/0maAjqXQdkxhwSrYZO6G\n" + 
			"6QIDAQAB\n" + 
			"-----END PUBLIC KEY-----";
	
}
