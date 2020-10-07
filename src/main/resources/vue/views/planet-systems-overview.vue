<template id="planet-system-overview">
   <div>
       <h1>Planet systems</h1>

       <p class="center"><a :href="`/planet-systems/createPlanetSystem`" class="button" class="add">Create a Planet System</a></p>

        <ul class="planet-system-overview-list">
            <li v-for="planetSystem in planetSystems">
                <a :href="`/planet-systems/${planetSystem.name.trim()}`" class="link-to-planet-system-details">
                    <div class="single-planet-system-container" >
                        <h1>{{planetSystem.name}}</h1>

                        <img v-if="planetSystem.pictureUrl" class="cover-image-frontpage" v-bind:src="planetSystem.pictureUrl">
                        <img v-else class="cover-image-frontpage" src="https://cdn.wallpapersafari.com/30/98/cOg2QG.jpg">
                    </div>
                </a>
            </li>
        </ul>
   </div>
</template>
<script>
    Vue.component("planet-systems-overview", {
        template: "#planet-system-overview",
        data: () => ({
            planetSystems: [],
        }),
        created() {
            fetch("/api/planet-systems")
                .then(res => res.json())
                .then(res => {
                   this.planetSystems = res;
                })
                .catch(() => alert("Error while fetching planetsystems"));
        }
    });
</script>
<style>
    li{
        list-style-type: none;
    }

    .planet-system-overview-list{
        display: flex;
        flex-wrap: wrap;
        justify-content: center;
    }

    .planet-system-overview-list li{
        padding: 10px;
        border: 1px solid white;
        border-radius: 15px;
    }

    .link-to-planet-system-details{
        width: 400px;
        height:100px;
        text-decoration: none;
        color: white;
    }

    div.single-planet-system-container{
        overflow: hidden;
        width: 500px;
        background-color: #000000;
        margin: 0 auto;
        opacity: 0.96;
        text-align: center;
    }

    div.single-planet-system-container:hover{
        opacity: 1.0;
        overflow: hidden;
        -webkit-box-shadow: 10px 10px 5px 0px rgba(0,0,0,0.25);
        -moz-box-shadow: 10px 10px 5px 0px rgba(0,0,0,0.25);
        box-shadow: 10px 10px 5px 0px rgba(0,0,0,0.25);
    }

    img.cover-image-frontpage {
        height: auto;
        width: 100%;
        padding-bottom: 20px;
        max-height: 280px;
    }

    .center {
        margin: 0 auto;
        display: flex;
        flex-direction: row;
    }

    .button {
        text-align: center;
        padding: 10px;
        margin: 0 auto;
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

    h1 {
        text-align: center;
        color: #fff;
        margin-bottom: 30px;
    }

    .center {
        display: flex;
        flex-direction: row;
        margin: 0 auto;
        text-align: center;
    }

    .center a {
        margin: 0 auto;
        text-align: center;
    }

</style>