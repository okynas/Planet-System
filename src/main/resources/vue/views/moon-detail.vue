<template id="moon-detail">
    <div v-if="moon" class="detail-planet-container">
        {{console.log(this.moon)}}
        <!-- /planet-systems/${planetSystemId}/planets/${planetId}/moon/${moonId} -->

        <a class="backBtn2" v-bind:href="'/planet-systems/' + planetSystemName + '/planets/' + planet ">Back</a>

        <h1>{{moon.name}}</h1>
        <img class="main-image"  v-if="moon.pictureUrl" v-bind:src="moon.pictureUrl" />
        <img v-else class="main-image" src="https://exoplanets.nasa.gov/system/resources/detail_files/137_heic1312a.jpg"/>

        <p>The mass of {{moon.name}} is {{moon.mass}} kg, it has a radius of {{moon.radius}} km,
            the eccentricity or the deviation of orbit is from a circularity is {{moon.eccentricity}}.</p>
        <p>Orbiting period of {{moon.orbitalPeriod}} days.</p>

        <!-- <p>
            <a class="button" :href="`/api/planet-systems/${planetSystemName}/planets/${planet}/moon/${moonId}/delete`">Delete</a>
            <a class="button" :href="`/planet-systems/${planetSystemName}/planets/${planet.name}/update`">Edit</a>
        </p>-->

        <!-- <ul class="list-of-moons">
            <li v-for="moon in moons" class="link-to-planet-details" >
                <div v-if="moon">
                    <p>{{moon.name}}</p>
                    <span v-if="planet" class="link-to-moon-details">
                        <div class="single-planet-container" >
                            <h1>{{moon.name}}</h1>
                            <img v-if="moon.pictureUrl" class="list-image" v-bind:src="moon.pictureUrl">
                            <img v-else class="list-image" src="https://upload.wikimedia.org/wikipedia/commons/thumb/d/d9/Icon-round-Question_mark.svg/480px-Icon-round-Question_mark.svg.png">
                        </div>
                    </span>
                </div>
            </li>
        </ul> -->
    </div>
</template>
<script>
    Vue.component("moon-detail", {
        template: "#moon-detail",
        data: () => ({
            planet: "",
            moon: null,
            moonId: "",
            planetSystemName: "",
        }),
        created() {
            const planetSystemId = this.$javalin.pathParams["planet-system-id"];
            this.planetSystemName = planetSystemId;
            const planetId = this.$javalin.pathParams["planet-id"];
            this.planet = planetId;
            const moonId = this.$javalin.pathParams["moon-id"];
            this.moonId = moonId;
            fetch(`/api/planet-systems/${planetSystemId}/planets/${planetId}/moon/${moonId}`)

                .then(res => res.json())
                .then(res => {
                    this.moon = res
                })
                .catch(() => alert("Error while fetching planet"));
        }
    });
</script>
<style>

    img.main-image {
        width: 400px;
    }

    ul{
       color:white;
    }
    div.detail-planet-container > p {
        max-width: 30em;
    }
    div.detail-planet-container{
        padding-top: 10px;
        /*overflow: hidden;*/
        width: 500px;
        background-color: #000000;
        color: white;
        margin: 0 auto;
        opacity: 0.96;
        text-align: center;
        -webkit-box-shadow: 10px 10px 5px 0px rgba(0,0,0,0.25);
        -moz-box-shadow: 10px 10px 5px 0px rgba(0,0,0,0.25);
        box-shadow: 10px 10px 5px 0px rgba(0,0,0,0.25);
    }

    img.planet-cover-image {
        height: 320px;
        width: 320px;
        padding-bottom: 20px;
    }

    .button {
        padding: 10px;
        margin: 10px;
        border: 1px solid white;
        color: white;
        border-radius: 15px;
    }

    .button a{
        color: white;
        text-decoration: none;
        font-weight: bold;
    }

    .button:hover{
        border: 2px solid white;
    }

    .link-to-planet-details{
        width: 400px;
        height:100px;
        text-decoration: none;
        color:black;
    }

    .list-of-moons {
        display: flex;
        flex-direction: row;
    }

</style>