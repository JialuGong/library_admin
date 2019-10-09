<template>
  <div class="demo-image__placeholder">
    <div class="block">
      <el-image :src="src">
      <div slot="placeholder" class="image-slot">
        loading...<span class="dot">...</span>
      </div>
    </el-image>
    </div>
  </div>
</template>

<script>
export default {
    data() {
        return {
            src:
                'https://cdn-ossd.zipjpg.com/free/157a0cdbc2c9e3cac0294852b06bb8f1_2_2_art.png'
        }
    }
}
</script>
