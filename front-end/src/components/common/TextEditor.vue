<template>
  <div>
    <div class="sample-toolbar">
		<a href="javascript:void(0)" @click="format('bold')"><span class="fa fa-bold fa-fw"></span></a>
		<a href="javascript:void(0)" @click="format('italic')"><span class="fa fa-italic fa-fw"></span></a>
		<a href="javascript:void(0)" @click="format('underline')"><span class="fas fa-underline fa-fw"></span></a>
		<a href="javascript:void(0)" @click="setUrl()"><span class="fa fa-link fa-fw"></span></a>
    <select name="job" id='test' v-model="size" @click="changeSize">
      <option value="1">1</option>
      <option value="2">2</option>
      <option value="3">3</option>
      <option value="4">4</option>
      <option value="5">5</option>
      <option value="6">6</option>
      <option value="7">7</option>
    </select>
		<span><input id="txtFormatUrl" placeholder="url" class="form-control"></span>
    </div>
    <p
      v-for="(value, index) in content"
      id="paragraph"
      :key="index"
      contenteditable
      @input="event => onInput(event, index)"
      @keyup.delete="onRemove(index)"
      class="editor"
    />
  </div>
</template>

<script>
export default {
  data() {
    return {
      content: [
        { value: 'paragraph' },
      ],
      size: 3
    };
  },
  mounted() {
    document.getElementById('paragraph').setAttribute('contenteditable', 'true');
    this.updateAllContent();
  },
  methods: {
    onInput(event, index) {
      const value = event.target.innerText;
      this.content[index].value = value;
      console.log(event.target.innerHTML)
    },
    onRemove(index) {
      if (this.content.length > 1 && this.content[index].value.length === 0) {
        this.$delete(this.content, index);
        this.updateAllContent();
      }
    },
    updateAllContent() {
      this.content.forEach((c) => {
        const el = document.getElementById('paragraph');
        el.innerText = c.value;
      });
    },
    format(command, value) {
		document.execCommand(command, false, value);
    },
    setUrl() {
		var url = document.getElementById('txtFormatUrl').value;
		var sText = document.getSelection();
		document.execCommand('insertHTML', false, '<a href="' + url + '" target="_blank">' + sText + '</a>');
		document.getElementById('txtFormatUrl').value = '';
  },
    changeSize() {
      this.size *= 1
      document.execCommand('fontSize', false,this.size)
    }
  },
};
</script>