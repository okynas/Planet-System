<template id="planet-systems-create" @planet-submitted="createPlanetSystem">
    <div class="form-style">
        <h2>Create new PlanetSystem</h2>
        <form class="create" @submit="checkForm" :action=`/api/planet-systems/createPlanetSystem` method="post">
            <div v-if="errors.length">
                <b>Please correct the following error(s):</b>
                <ul>
                    <li v-for="error in errors">{{ error }}</li>
                </ul>
            </div>

            <p>
                <label for="name">Planet System Name<label>
                    <input type="text" name="name" id="name" v-model="name">
            </p>

            <p>
                <label for="star">Star Name<label>
                    <input type="text"  name="star" id="star" v-model="star">
            </p>

            <p>
                <label for="mass">Star Mass<label>
                    <input type="number" step="0.000001" name="mass" id="mass" v-model="mass" min="0">
            </p>

            <p>
                <label for="radius">Star Radius<label>
                    <input type="number" step="0.000001" name="radius" id="radius" v-model="radius" min="0">
            </p>

            <p>
                <label for="effectiveTemperature">Effective Temperature<label>
                    <input type="number" step="1" name="effectiveTemperature" id="effectiveTemperature" v-model="effectiveTemperature" min="0">
            </p>
            <p>
                <label for="starPictureUrl">Star Picture Url<label>
                    <input type="text" name="starPictureUrl" id="starPictureUrl" v-model="starPictureUrl">
            </p>
            <p>
                <label for="planetSystemPictureUrl">Planet System Picture Url<label>
                    <input type="text" name="planetSystemPictureUrl" id="planetSystemPictureUrl" v-model="planetSystemPictureUrl" />
            </p>



            <p>
                <input type="submit" value="Create Planet System">
            </p>

        </form>
    </div>

</template>
<script>
    Vue.component("planet-systems-create", {
        template: "#planet-systems-create",
        data: () => ({
            name: null,
            star: null,
            mass: null,
            radius: null,
            effectiveTemperature: null,
            starPictureUrl: null,
            planetSystemName: null,
            planetSystemPictureUrl: null,
            errors: []
        }),
        created() {
            this.planetSystemName = this.$javalin.pathParams["planet-system-id"];
        },
        methods:{
            checkForm:function(e) {
                const urlRegex = "/^(?:(?:(?:https?|ftp):)?\\/\\/)(?:\\S+(?::\\S*)?@)?(?:(?!(?:10|127)(?:\\.\\d{1,3}){3})(?!(?:169\\.254|192\\.168)(?:\\.\\d{1,3}){2})(?!172\\.(?:1[6-9]|2\\d|3[0-1])(?:\\.\\d{1,3}){2})(?:[1-9]\\d?|1\\d\\d|2[01]\\d|22[0-3])(?:\\.(?:1?\\d{1,2}|2[0-4]\\d|25[0-5])){2}(?:\\.(?:[1-9]\\d?|1\\d\\d|2[0-4]\\d|25[0-4]))|(?:(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)(?:\\.(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)*(?:\\.(?:[a-z\u00a1-\uffff]{2,})))(?::\\d{2,5})?(?:[/?#]\\S*)?$/i";
                if(this.name && this.mass && this.radius) return true;
                this.errors = [];
                if (!this.name) this.errors.push("Name required");
                if (!this.mass) this.errors.push("Mass required");
                if (!this.radius) this.errors.push("Radius required");
                e.preventDefault();
            }
        }
    });
</script>
<style>
    .form-style{
        font-family: 'Open Sans Condensed', arial, sans;
        width: 500px;
        padding: 30px;
        background: #191919;
        margin: 50px auto;
        box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.22);
        -moz-box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.22);
        -webkit-box-shadow:  0px 0px 15px rgba(0, 0, 0, 0.22);
        border: #dddddd;
    }
    .form-style h2{
        background: #4D4D4D;
        text-transform: uppercase;
        font-family: 'Open Sans Condensed', sans-serif;
        color: #FFFFFF;
        font-size: 18px;
        font-weight: 100;
        padding: 20px;
        margin: -30px -30px 30px -30px;
    }
    .form-style input[type="text"],
    .form-style input[type="date"],
    .form-style input[type="datetime"],
    .form-style input[type="email"],
    .form-style input[type="number"],
    .form-style input[type="search"],
    .form-style input[type="time"],
    .form-style input[type="url"],
    .form-style input[type="password"],
    .form-style textarea,
    .form-style select
    {
        box-sizing: border-box;
        -webkit-box-sizing: border-box;
        -moz-box-sizing: border-box;
        outline: none;
        display: block;
        width: 100%;
        padding: 7px;
        border: none;
        color: white;
        border-bottom: 1px solid #ddd;
        background: transparent;
        margin-bottom: 10px;
        font: 16px Arial, Helvetica, sans-serif;
        height: 45px;
    }
    .form-style textarea{
        resize:none;
        overflow: hidden;
    }
    .form-style input[type="button"],
    .form-style input[type="submit"]{
        background: none;
        display: inline-block;
        cursor: pointer;
        font-family: 'Open Sans Condensed', sans-serif;
        font-size: 14px;
        text-decoration: none;
        text-transform: uppercase;
        padding: 10px;
        margin: 10px;
        border: 1px solid white;
        color: white;
        border-radius: 15px;
    }
    .form-style input[type="button"]:hover,
    .form-style input[type="submit"]:hover {
        border: 2px solid white;
    }

    .create {
        color: white;
    }
</style>
