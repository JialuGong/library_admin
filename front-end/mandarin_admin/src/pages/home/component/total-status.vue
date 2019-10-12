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
                'http://47.92.141.59/images/bg.png'
        }
    }
}
</script>
