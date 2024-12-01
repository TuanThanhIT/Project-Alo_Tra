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
    const citySelect = document.getElementById("city");
    const districtSelect = document.getElementById("district");
    const wardSelect = document.getElementById("ward");
    const addressInput = document.getElementById("address");

    if (!citySelect || !districtSelect || !wardSelect || !addressInput) {
        console.error("One or more required elements are missing in the DOM.");
        return;
    }

    const Parameter = {
        url: "https://raw.githubusercontent.com/kenzouno1/DiaGioiHanhChinhVN/master/data.json",
        method: "GET",
        responseType: "application/json",
    };

    axios(Parameter).then(function (result) {
        const data = result.data;
        renderCity(data);
    }).catch(function (error) {
        console.error("Error fetching data:", error);
    });

    function renderCity(data) {
        data.forEach(city => {
            const option = new Option(city.Name, city.Id);
            citySelect.appendChild(option);
        });

        citySelect.addEventListener("change", function () {
            districtSelect.innerHTML = '<option value="" selected>Chọn quận huyện</option>';
            wardSelect.innerHTML = '<option value="" selected>Chọn phường xã</option>';

            if (this.value !== "") {
                const selectedCity = data.find(city => city.Id === this.value);
                selectedCity.Districts.forEach(district => {
                    const option = new Option(district.Name, district.Id);
                    districtSelect.appendChild(option);
                });
            }

            updateAddress();
        });

        districtSelect.addEventListener("change", function () {
            wardSelect.innerHTML = '<option value="" selected>Chọn phường xã</option>';

            if (this.value !== "") {
                const selectedCity = data.find(city => city.Id === citySelect.value);
                const selectedDistrict = selectedCity.Districts.find(district => district.Id === this.value);

                selectedDistrict.Wards.forEach(ward => {
                    const option = new Option(ward.Name, ward.Id);
                    wardSelect.appendChild(option);
                });
            }

            updateAddress();
        });

        wardSelect.addEventListener("change", updateAddress);
    }

    function updateAddress() {
        const city = citySelect.options[citySelect.selectedIndex]?.text || "";
        const district = districtSelect.options[districtSelect.selectedIndex]?.text || "";
        const ward = wardSelect.options[wardSelect.selectedIndex]?.text || "";

        addressInput.value = `${ward}, ${district}, ${city}`.replace(/, $/, "").replace(/^, /, "");
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

// Lưu thời gian mở cửa của seller từ 3 combo box khác nhau
document.addEventListener("DOMContentLoaded", function() {
    const openHourSelect = document.getElementById("open-hour");
    const openMinuteSelect = document.getElementById("open-minute");
    const openSecondSelect = document.getElementById("open-second");
    const openTimeInput = document.getElementById("open-time");

    function updateOpenTime() {
        const hour = openHourSelect.value || "00";
        const minute = openMinuteSelect.value || "00";
        const second = openSecondSelect.value || "00";

        // Gộp thành chuỗi HH:mm:ss
        openTimeInput.value = `${hour}:${minute}:${second}`;
    }

    // Lắng nghe sự kiện thay đổi
    openHourSelect.addEventListener("change", updateOpenTime);
    openMinuteSelect.addEventListener("change", updateOpenTime);
    openSecondSelect.addEventListener("change", updateOpenTime);
});



// Lưu địa chỉ chi nhánh từ 3 combo box khác nhau

// Lưu thời gian đóng cửa của seller từ 3 combo box khác nhau
document.addEventListener("DOMContentLoaded", function() {
    const hourSelect = document.getElementById("close-hour");
    const minuteSelect = document.getElementById("close-minute");
    const secondSelect = document.getElementById("close-second");
    const closeTimeInput = document.getElementById("close-time");

    function updateCloseTime() {
        const hour = hourSelect.value || "00";
        const minute = minuteSelect.value || "00";
        const second = secondSelect.value || "00";

        // Gộp thành chuỗi HH:mm:ss
        closeTimeInput.value = `${hour}:${minute}:${second}`;
    }

    // Lắng nghe sự kiện thay đổi
    hourSelect.addEventListener("change", updateCloseTime);
    minuteSelect.addEventListener("change", updateCloseTime);
    secondSelect.addEventListener("change", updateCloseTime);
});



