<template id="planet-detail">
    <div v-if="planet" class="detail-planet-container">
        {{console.log(this.planet)}}
        <a class="backBtn2" v-bind:href="'/planet-systems/' + planetSystemName ">Back</a>
        <h1>{{planet.name}}</h1>
        <img v-if="planet.pictureUrl" v-bind:src="planet.pictureUrl" />
        <img v-else class="list-image" src="https://exoplanets.nasa.gov/system/resources/detail_files/137_heic1312a.jpg"/>

        <p>The mass of {{planet.name}} is {{planet.mass}} kg, it has a radius of {{planet.radius}} km,
            the eccentricity or the deviation of orbit is from a circularity is {{planet.eccentricity}}.</p>
        <p>It spins around the star {{planet.centralCelestialBody.name}} with an orbiting period of {{planet.orbitalPeriod}} days.</p>

        <p>
            <a class="button" :href="`/api/planet-systems/${planetSystemName}/planets/${planet.name}/delete`">Delete</a>
            <a class="button" :href="`/planet-systems/${planetSystemName}/planets/${planet.name}/update`">Edit</a>
        </p>

        <h2>Moons</h2>

        <p>
            <!-- <a class="button" :href="`/api/planet-systems/${planetSystemName}/planets/${planet.name}/delete`">Delete</a> -->
            <!-- <a class="button" :href="`/planet-systems/${planetSystemName}/planets/${planet.name}/update`">Edit</a> -->
            <a class="button" :href="`/planet-systems/${planetSystemName}/planets/${planet.name}/createmoon`">Create</a>
        </p>

        <ul class="list-of-moons">
            <li v-for="moon in moons" class="link-to-planet-details" >
                <div v-if="moon">
                    <p>{{moon.name}}</p>
                    <a v-if="planet" class="link-to-moon-details" v-bind:href="'/planet-systems/' + planetSystemName + '/planets/' + planet.name +'/moon/' + moon.name">
                        <div class="single-planet-container" >
                            <h1>{{moon.name}}</h1>
                            <img v-if="moon.pictureUrl" class="list-image" v-bind:src="moon.pictureUrl">
                            <img v-else class="list-image" src="https://exoplanets.nasa.gov/system/resources/detail_files/137_heic1312a.jpg">
                        </div>
                    </a>
                </div>
            </li>
        </ul>

    </div>
</template>
<script>
    Vue.component("planet-detail", {
        template: "#planet-detail",
        data: () => ({
            planet: null,
            moons: [],
            planetSystemName: "",
        }),
        created() {
            const planetSystemId = this.$javalin.pathParams["planet-system-id"];
            this.planetSystemName = planetSystemId;
            console.log("Planet system id: " + planetSystemId);
            const planetId = this.$javalin.pathParams["planet-id"];
            fetch(`/api/planet-systems/${planetSystemId}/planets/${planetId}`)

                .then(res => res.json())
                .then(res => {
                    this.planet = res;
                    console.log(res)
                    this.moons = res.moon;
                })
                .catch(() => alert("Error while fetching planet"));
        }
    });
</script>
<style>

    .backBtn2 {
        text-align: center;
        color: white;
        margin-top: 40px;
        margin-bottom: 40px;
        font-size: 20px;
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