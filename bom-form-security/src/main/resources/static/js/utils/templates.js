export const initNavigation = () => {
    document.querySelector('body').insertAdjacentHTML('afterBegin', navTemplate)
}

const navTemplate = `
<nav class="flex items-center justify-between flex-wrap bg-teal-500 p-4 text-white text-sm">
  <div class="flex items-center flex-shrink-0 w-full">
    <div class="flex justify-start">
      <div class="hover:bg-teal-600 px-2 py-1 rounded">
        <a href="/" class="block inline-block lg:mt-0">
          홈 페이지
        </a>
      </div>
      <div class="hover:bg-teal-600 px-2 py-1 rounded">
         <a href="/notices" class="block inline-block lg:mt-0">
          공지 사항
          </a>
      </div>
      <div class="hover:bg-teal-600 px-2 py-1 rounded">
         <a href="/articles" class="block inline-block lg:mt-0">
          자유 게시판
          </a>
      </div>
      <div class="hover:bg-teal-600 px-2 py-1 rounded">
          <a href="/admin" class="block inline-block lg:mt-0">
          관리자 메뉴
          </a>
      </div>
    </div>
    <div class="flex ml-auto">
      <div id="sign-up-page" class="hover:bg-teal-600 px-2 py-1 rounded">
        <a href="/sign-up" class="block inline-block lg:mt-0">
          회원 가입
         </a>
      </div>
      <div id="login-page" class="hover:bg-teal-600 px-2 py-1 rounded">        
        <a href="/login" class="block inline-block lg:mt-0">
          로그인
        </a>
      </div>
      <div id="logout-button" class="hover:bg-teal-600 px-2 py-1 rounded">        
        <button class="block inline-block lg:mt-0">
          로그아웃
        </button>
      </div>
    </div>
</div>
</nav>
`
