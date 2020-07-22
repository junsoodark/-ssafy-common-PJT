<template>
  <div>
    <h1>헤더 {{IsLoggedIn}}</h1>
  </div>
</template>

<script>
export default {
    props: {
        IsLoggedIn: Boolean,
    }
}
</script>

<style>

</style>