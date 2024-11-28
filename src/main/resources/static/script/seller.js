function openSidebar() {
    document.getElementById("sidebar").style.width = "250px";
    document.getElementById("overlay").style.display = "block"; // Hiển thị overlay
}

function closeSidebar() {
    document.getElementById("sidebar").style.width = "0";
    document.getElementById("overlay").style.display = "none"; // Ẩn overlay
}
//Tạo combobox chọn địa chỉ
document.addEventListener("DOMContentLoaded", function () {
    var citis = document.getElementById("city");
    var districts = document.getElementById("district");
    var wards = document.getElementById("ward");

    if (!citis || !districts || !wards) {
        console.error("One or more comboboxes are missing in the DOM.");
        return;
    }

    var Parameter = {
        url: "https://raw.githubusercontent.com/kenzouno1/DiaGioiHanhChinhVN/master/data.json",
        method: "GET",
        responseType: "application/json",
    };

    axios(Parameter).then(function (result) {
        renderCity(result.data);
    }).catch(function (error) {
        console.error("Error fetching data:", error);
    });

    function renderCity(data) {
        for (const x of data) {
            citis.options[citis.options.length] = new Option(x.Name, x.Id);
        }

        citis.onchange = function () {
            districts.length = 1;
            wards.length = 1;

            if (this.value !== "") {
                const result = data.filter(n => n.Id === this.value);

                for (const k of result[0].Districts) {
                    districts.options[districts.options.length] = new Option(k.Name, k.Id);
                }
            }
        };

        districts.onchange = function () {
            wards.length = 1;

            const dataCity = data.filter(n => n.Id === citis.value);

            if (this.value !== "") {
                const dataWards = dataCity[0].Districts.filter(n => n.Id === this.value)[0].Wards;

                for (const w of dataWards) {
                    wards.options[wards.options.length] = new Option(w.Name, w.Id);
                }
            }
        };
    }
});

//Tạo combobox cho giờ
document.addEventListener("DOMContentLoaded", function () {
    // Thời gian mở cửa
    const openHour = document.getElementById("open-hour");
    const openMinute = document.getElementById("open-minute");
    const openSecond = document.getElementById("open-second");

    // Thời gian đóng cửa
    const closeHour = document.getElementById("close-hour");
    const closeMinute = document.getElementById("close-minute");
    const closeSecond = document.getElementById("close-second");

    // Hàm tạo các tùy chọn cho thẻ select
    function populateSelect(selectElement, range) {
        for (let i = 0; i < range; i++) {
            const option = document.createElement("option");
            option.value = i;
            option.textContent = i.toString().padStart(2, "0");
            selectElement.appendChild(option);
        }
    }

    // Tạo dữ liệu cho cả mở cửa và đóng cửa
    populateSelect(openHour, 24);
    populateSelect(openMinute, 60);
    populateSelect(openSecond, 60);

    populateSelect(closeHour, 24);
    populateSelect(closeMinute, 60);
    populateSelect(closeSecond, 60);
});


// Optional: Add functionality if you want to pause on hover or add controls
document.addEventListener("DOMContentLoaded", function () {
    const slider = document.querySelector(".image-slider");

    slider.addEventListener("mouseover", () => {
        slider.style.animationPlayState = "paused";
    });

    slider.addEventListener("mouseout", () => {
        slider.style.animationPlayState = "running";
    });
});


