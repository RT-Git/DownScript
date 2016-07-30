
<html>
	<body>
		<?php
			$link = "";

			if ($_SERVER["REQUEST_METHOD"] == "GET") {
			  $link = test_input($_GET["link"]);
			}

			function test_input($data) {
			  $data = trim($data);
			  $data = stripslashes($data);
			  $data = htmlspecialchars($data);
			  return $data;
			}
			$link = $link." - n";
			$myfile = file_put_contents('links.txt', $link.PHP_EOL , FILE_APPEND);
			fclose($myfile);
		?>
	<script type="text/javascript">
	window.location.replace("http://barca.tk/33");
	</script>
	
	</body>
</html>

